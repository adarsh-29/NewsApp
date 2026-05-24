package com.example.newsapp.feature_news.presentation.bookmarks
import com.example.newsapp.feature_news.domain.model.Article

data class BookmarksUiState(

    val articles:
    List<Article> = emptyList()
)