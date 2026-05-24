package com.example.newsapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.feature_news.data.local.dao.ArticleDao
import com.example.newsapp.feature_news.data.local.dao.RemoteKeysDao
import com.example.newsapp.feature_news.data.local.entity.ArticleEntity
import com.example.newsapp.feature_news.data.local.entity.RemoteKeys

@Database(
    entities = [
        ArticleEntity::class,
        RemoteKeys::class
    ],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}