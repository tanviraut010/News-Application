package com.example.newsapplication.network

import com.example.newsapplication.data.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("top-headlines")
    fun fetchNewsList(
        @Query("apiKey") apiKey: String,
        @Query("Country") country: String
    ): Call<NewsResponse>
}