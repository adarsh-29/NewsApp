package com.example.newsapp.di

import com.example.newsapp.feature_news.domain.repository.NewsRepository
import com.example.newsapp.feature_news.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideNewsUseCases(
        repository: NewsRepository
    ): NewsUseCases {

        return NewsUseCases(
            getTopHeadlines =
                GetTopHeadlinesUseCase(repository),

            searchNews =
                SearchNewsUseCase(repository),

            getBookmarks =
                GetBookmarksUseCase(repository),

            toggleBookmark =
                ToggleBookmarkUseCase(repository),

            getArticle =
                GetArticleUseCase(repository)
        )
    }
}