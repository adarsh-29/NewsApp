package com.example.newsapp.core.worker

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

object WorkScheduler {

    fun schedule(
        context: Context
    ) {

        val request =
            PeriodicWorkRequestBuilder<
                    NewsSyncWorker>(
                24,
                TimeUnit.HOURS
            ).build()

        WorkManager
            .getInstance(context)
            .enqueueUniquePeriodicWork(

                "daily_news_sync",

                ExistingPeriodicWorkPolicy
                    .KEEP,

                request
            )
    }
}