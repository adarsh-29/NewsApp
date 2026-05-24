package com.example.newsapp.feature_news.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.newsapp.feature_news.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles ORDER BY publishedAt DESC")
    fun pagingSource(): PagingSource<Int, ArticleEntity>

    @Query("SELECT * FROM articles WHERE isBookmarked = 1 ORDER BY publishedAt DESC")
    fun getBookmarks(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(
        articles: List<ArticleEntity>
    )

    @Update
    suspend fun updateArticle(
        article: ArticleEntity
    )

    @Query("DELETE FROM articles")
    suspend fun clearArticles()

    @Query("SELECT * FROM articles WHERE url = :url LIMIT 1")
    suspend fun getArticle(
        url: String
    ): ArticleEntity?
}