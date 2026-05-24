package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.core.database.NewsDatabase
import com.example.newsapp.core.util.Constants
import com.example.newsapp.feature_news.data.local.dao.ArticleDao
import com.example.newsapp.feature_news.data.local.dao.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase {

        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            Constants.NEWS_DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideArticleDao(
        database: NewsDatabase
    ): ArticleDao = database.articleDao()

    @Provides
    fun provideRemoteKeysDao(
        database: NewsDatabase
    ): RemoteKeysDao = database.remoteKeysDao()
}