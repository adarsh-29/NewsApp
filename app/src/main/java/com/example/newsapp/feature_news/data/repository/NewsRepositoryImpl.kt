package com.example.newsapp.feature_news.data.repository

import androidx.paging.*
import com.example.newsapp.core.database.NewsDatabase
import com.example.newsapp.core.util.Constants
import com.example.newsapp.feature_news.data.mapper.toDomain
import com.example.newsapp.feature_news.data.paging.NewsRemoteMediator
import com.example.newsapp.feature_news.data.paging.SearchNewsPagingSource
import com.example.newsapp.feature_news.data.remote.api.NewsApiService
import com.example.newsapp.feature_news.domain.model.Article
import com.example.newsapp.feature_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class NewsRepositoryImpl(
    private val api: NewsApiService,
    private val database: NewsDatabase,
    private val bookmarkLocalDataSource: BookmarkLocalDataSource
) : NewsRepository {

    override fun getTopHeadlines():
            Flow<PagingData<Article>> {

        return Pager(

            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                prefetchDistance =
                    Constants.PREFETCH_DISTANCE
            ),

            remoteMediator =
                NewsRemoteMediator(
                    api,
                    database
                ),

            pagingSourceFactory = {
                database
                    .articleDao()
                    .pagingSource()
            }

        ).flow.map {

            it.map { entity ->
                entity.toDomain()
            }
        }
    }

    override fun searchNews(
        query: String
    ): Flow<PagingData<Article>> {

        return Pager(
            config = PagingConfig(
                pageSize =
                    Constants.PAGE_SIZE
            ),

            pagingSourceFactory = {

                SearchNewsPagingSource(
                    api,
                    query
                )
            }

        ).flow
    }

    override fun getBookmarks():
            Flow<List<Article>> {

        return database
            .articleDao()
            .getBookmarks()
            .map { list ->

                list.map {
                    it.toDomain()
                }
            }
    }

    override suspend fun toggleBookmark(
        url: String
    ) {

        bookmarkLocalDataSource
            .toggleBookmark(url)
    }

    override suspend fun getArticle(
        url: String
    ): Article? {

        return database
            .articleDao()
            .getArticle(url)
            ?.toDomain()
    }
}