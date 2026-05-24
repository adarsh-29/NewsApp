package com.example.newsapp.feature_news.domain.usecase

data class NewsUseCases(

    val getTopHeadlines:
    GetTopHeadlinesUseCase,

    val searchNews:
    SearchNewsUseCase,

    val getBookmarks:
    GetBookmarksUseCase,

    val toggleBookmark:
    ToggleBookmarkUseCase,

    val getArticle:
    GetArticleUseCase
)