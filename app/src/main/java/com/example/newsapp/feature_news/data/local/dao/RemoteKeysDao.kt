package com.example.newsapp.feature_news.data.local.dao


import androidx.room.*
import com.example.newsapp.feature_news.data.local.entity.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(
        remoteKeys: List<RemoteKeys>
    )

    @Query(
        "SELECT * FROM remote_keys WHERE articleUrl = :url"
    )
    suspend fun remoteKeysUrl(
        url: String
    ): RemoteKeys?

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}