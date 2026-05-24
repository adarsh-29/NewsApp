package com.example.newsapp.feature_news.domain.usecase

import com.example.newsapp.feature_news.domain.repository.NewsRepository
import javax.inject.Inject

class GetBookmarksUseCase
@Inject constructor(
    private val repository:
    NewsRepository
) {

    operator fun invoke() =
        repository.getBookmarks()
}