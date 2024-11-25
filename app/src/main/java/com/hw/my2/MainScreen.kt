package com.hw.my2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hw.my2.viewmodel.MainViewModel
import com.hw.my2.GifImage
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val gifList = viewModel.gifList
    val loading by viewModel.loading
    val errorMessage by viewModel.errorMessage

    if (loading && gifList.isEmpty()) {
        // Отображаем крутилку при первой загрузке
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (errorMessage != null && gifList.isEmpty()) {
        // Отображаем заглушку с возможностью повторной загрузки
        ErrorPlaceholder(message = errorMessage, onRetry = { viewModel.fetchGifs() })
    } else {
        LazyColumn {
            itemsIndexed(gifList) { index, gif ->
                GifImage(url = gif.images.original.url)
                if (index == gifList.lastIndex && !loading && errorMessage == null) {
                    // Инициируем догрузку при достижении конца списка
                    viewModel.fetchGifs()
                }
            }
            item {
                when {
                    loading -> {
                        // Отображаем крутилку при догрузке
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                        }
                    }
                    errorMessage != null -> {
                        // Отображаем возможность повторной догрузки
                        RetryLoadMoreItem(message = errorMessage, onRetry = { viewModel.fetchGifs() })
                    }
                }
            }
        }
    }
}


@Composable
fun ErrorPlaceholder(message: String?, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(message ?: "Ошибка загрузки данных")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text("Повторить")
        }
    }
}

@Composable
fun RetryLoadMoreItem(message: String?, onRetry: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(message ?: "Ошибка догрузки данных")
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = onRetry) {
            Text("Повторить")
        }
    }
}
