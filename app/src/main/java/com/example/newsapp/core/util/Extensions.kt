package com.example.newsapp.core.util
import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatNewsDate(): String {

    return try {

        val inputFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            Locale.getDefault()
        )

        val outputFormat = SimpleDateFormat(
            "dd MMM yyyy",
            Locale.getDefault()
        )

        val date = inputFormat.parse(this)

        outputFormat.format(date!!)

    } catch (e: Exception) {
        this
    }
}