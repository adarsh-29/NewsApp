package com.example.newsapp.di

import com.example.newsapp.core.database.NewsDatabase
import com.example.newsapp.feature_news.data.remote.api.NewsApiService
import com.example.newsapp.feature_news.data.repository.BookmarkLocalDataSource
import com.example.newsapp.feature_news.data.repository.NewsRepositoryImpl
import com.example.newsapp.feature_news.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBookmarkDataSource(
        database: NewsDatabase
    ): BookmarkLocalDataSource {

        return BookmarkLocalDataSource(
            database.articleDao()
        )
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        api: NewsApiService,
        database: NewsDatabase,
        bookmarkLocalDataSource:
        BookmarkLocalDataSource
    ): NewsRepository {

        return NewsRepositoryImpl(
            api,
            database,
            bookmarkLocalDataSource
        )
    }
}