package com.example.newsapp.feature_news.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PagingError(
    message: String,
    onRetry: () -> Unit
) {

    Column(
        modifier =
            Modifier.fillMaxSize(),

        horizontalAlignment =
            Alignment.CenterHorizontally,

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = message
        )

        Spacer(
            modifier =
                Modifier.height(
                    12.dp
                )
        )

        Button(
            onClick =
                onRetry
        ) {

            Text("Retry")
        }
    }
}