package com.example.newsapp.feature_news.domain.usecase

import com.example.newsapp.feature_news.domain.repository.NewsRepository
import javax.inject.Inject

class GetArticleUseCase
@Inject constructor(
    private val repository:
    NewsRepository
) {

    suspend operator fun invoke(
        url: String
    ) = repository.getArticle(url)
}