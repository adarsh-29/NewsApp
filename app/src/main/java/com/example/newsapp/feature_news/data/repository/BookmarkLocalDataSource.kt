package com.example.newsapp.feature_news.data.repository

import com.example.newsapp.feature_news.data.local.dao.ArticleDao

class BookmarkLocalDataSource(
    private val articleDao: ArticleDao
) {

    suspend fun toggleBookmark(
        url: String
    ) {

        val article =
            articleDao.getArticle(url)
                ?: return

        articleDao.updateArticle(
            article.copy(
                isBookmarked =
                    !article.isBookmarked
            )
        )
    }
}