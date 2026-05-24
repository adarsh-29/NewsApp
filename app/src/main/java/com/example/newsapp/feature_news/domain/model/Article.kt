package com.example.newsapp.feature_news.domain.model

data class Article(
    val sourceName: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String,
    val isBookmarked: Boolean = false
)