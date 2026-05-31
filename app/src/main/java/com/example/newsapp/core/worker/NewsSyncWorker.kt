package com.example.newsapp.core.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.example.newsapp.feature_news.domain.usecase.NewsUseCases
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

@HiltWorker
class NewsSyncWorker
@AssistedInject constructor(

    @Assisted
    context: Context,

    @Assisted
    params: WorkerParameters,

    private val useCases:
    NewsUseCases

) : CoroutineWorker(
    context,
    params
) {

    override suspend fun doWork():
            Result {

        return try {

            useCases
                .getTopHeadlines()
                .first()

            NotificationHelper.show(
                applicationContext,
                "News Updated",
                "Latest headlines are ready"
            )

            Result.success()

        } catch (e: Exception) {

            Result.retry()
        }
    }
}