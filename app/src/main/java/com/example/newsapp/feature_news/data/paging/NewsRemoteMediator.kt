package com.example.newsapp.feature_news.data.paging

import androidx.paging.*
import androidx.room.withTransaction
import com.example.newsapp.core.database.NewsDatabase
import com.example.newsapp.core.util.Constants
import com.example.newsapp.feature_news.data.local.entity.RemoteKeys
import com.example.newsapp.feature_news.data.mapper.toEntity
import com.example.newsapp.feature_news.data.remote.api.NewsApiService

@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val api: NewsApiService,
    private val database: NewsDatabase
) : RemoteMediator<Int, com.example.newsapp.feature_news.data.local.entity.ArticleEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, com.example.newsapp.feature_news.data.local.entity.ArticleEntity>
    ): MediatorResult {

        val page = when (loadType) {

            LoadType.REFRESH -> 1

            LoadType.PREPEND -> {
                return MediatorResult.Success(
                    endOfPaginationReached = true
                )
            }

            LoadType.APPEND -> {
                val lastItem =
                    state.lastItemOrNull()

                val remoteKey =
                    lastItem?.let {
                        database
                            .remoteKeysDao()
                            .remoteKeysUrl(it.url)
                    }

                remoteKey?.nextKey
                    ?: return MediatorResult.Success(
                        true
                    )
            }
        }

        return try {

            val response =
                api.getTopHeadlines(
                    page = page,
                    pageSize = Constants.PAGE_SIZE
                )

            val articles =
                response.body()?.articles.orEmpty()

            database.withTransaction {

                if (loadType == LoadType.REFRESH) {

                    database.remoteKeysDao()
                        .clearRemoteKeys()

                    database.articleDao()
                        .clearArticles()
                }

                val keys =
                    articles.map {

                        RemoteKeys(
                            articleUrl = it.url.orEmpty(),
                            prevKey =
                                if (page == 1) null else page - 1,
                            nextKey =
                                if (articles.isEmpty())
                                    null
                                else page + 1
                        )
                    }

                database.remoteKeysDao()
                    .insertAll(keys)

                database.articleDao()
                    .insertArticles(
                        articles.map {
                            it.toEntity()
                        }
                    )
            }

            MediatorResult.Success(
                endOfPaginationReached =
                    articles.isEmpty()
            )

        } catch (e: Exception) {

            MediatorResult.Error(e)
        }
    }
}