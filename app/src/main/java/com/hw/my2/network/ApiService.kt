package com.hw.my2.network

import com.hw.my2.model.GiphyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/gifs/trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<GiphyResponse>
}
