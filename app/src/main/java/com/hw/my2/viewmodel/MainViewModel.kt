package com.hw.my2.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hw.my2.model.GifData
import com.hw.my2.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _gifList = mutableStateListOf<GifData>()
    val gifList: List<GifData> = _gifList

    private var _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private var _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private var offset = 0
    private val limit = 20

    init {
        fetchGifs()
    }

    fun fetchGifs() {
        viewModelScope.launch {
            _loading.value = true
            _errorMessage.value = null
            try {
//                Log.d("MainViewModel", "Fetching GIFs: offset = $offset")
                val response = RetrofitInstance.api.getTrendingGifs(
                    apiKey = "UYPipg5KmrkIyt1CrLCPCFHaplCRG9lW",
                    limit = limit,
                    offset = offset
                )
                if (response.isSuccessful && response.body() != null) {
//                    Log.d("MainViewModel", "Successfully fetched data")
                    _gifList.addAll(response.body()!!.data)
                    offset += limit
                } else {
                    val errorBody = response.errorBody()?.string()
//                    Log.e("MainViewModel", "Response unsuccessful: ${response.code()} - $errorBody")
                    _errorMessage.value = "Ошибка сервера: ${response.code()}"
                }
            } catch (e: Exception) {
//                Log.e("MainViewModel", "Exception occurred: ${e.localizedMessage}")
                _errorMessage.value = "Ошибка сети: ${e.localizedMessage}"
            } finally {
                _loading.value = false
            }
        }
    }
}
