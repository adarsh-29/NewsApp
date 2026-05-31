package com.example.newsapp.core.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.newsapp.R

object NotificationHelper {

    private const val CHANNEL_ID =
        "news_channel"

    fun show(
        context: Context,
        title: String,
        body: String
    ) {

        val manager =
            context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

        val channel =
            NotificationChannel(
                CHANNEL_ID,
                "News Updates",
                NotificationManager.IMPORTANCE_DEFAULT
            )

        manager.createNotificationChannel(
            channel
        )

        val notification =
            NotificationCompat.Builder(
                context,
                CHANNEL_ID
            )
                .setSmallIcon(
                    R.drawable.ic_launcher_foreground
                )
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .build()

        manager.notify(
            1,
            notification
        )
    }
}