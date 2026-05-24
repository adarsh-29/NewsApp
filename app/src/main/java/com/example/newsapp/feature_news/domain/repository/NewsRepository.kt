package com.example.newsapp.feature_news.domain.repository

import androidx.paging.PagingData
import com.example.newsapp.feature_news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getTopHeadlines():
            Flow<PagingData<Article>>

    fun searchNews(
        query: String
    ): Flow<PagingData<Article>>

    fun getBookmarks():
            Flow<List<Article>>

    suspend fun toggleBookmark(
        url: String
    )

    suspend fun getArticle(
        url: String
    ): Article?
}