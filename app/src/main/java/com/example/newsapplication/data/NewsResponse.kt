package com.example.newsapplication.data

data class NewsResponse(
    val articles: ArrayList<Article>?,
    val status: String?,
    val totalResults: Int?
)