package com.example.newsapplication.ui.news

interface OnItemClick {
    fun onItemClickListener(
        imageUrl: String?, title: String?,
        author: String?, date: String?,
        content: String?, webViewUrl: String?
    )
}