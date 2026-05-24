package com.example.newsapp.feature_news.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.core.util.Constants
import com.example.newsapp.feature_news.data.mapper.toDomain
import com.example.newsapp.feature_news.data.remote.api.NewsApiService
import com.example.newsapp.feature_news.domain.model.Article

class SearchNewsPagingSource(
    private val api: NewsApiService,
    private val query: String
) : PagingSource<Int, Article>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Article> {

        return try {

            val page =
                params.key ?: 1

            val response =
                api.searchNews(
                    query = query,
                    page = page,
                    pageSize = Constants.PAGE_SIZE
                )

            val articles =
                response.body()
                    ?.articles
                    ?.map { it.toDomain() }
                    .orEmpty()

            LoadResult.Page(
                data = articles,
                prevKey =
                    if (page == 1)
                        null
                    else page - 1,
                nextKey =
                    if (articles.isEmpty())
                        null
                    else page + 1
            )

        } catch (e: Exception) {

            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, Article>
    ): Int? {

        return state.anchorPosition
    }
}