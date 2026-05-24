package com.example.newsapp.feature_news.data.remote.dto

data class NewsResponseDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)