package com.example.newsapp.feature_news.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.feature_news.domain.model.Article

@Composable
fun ArticleItem(
    article: Article,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .widthIn(max = 700.dp) // To support tablet
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            }
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = article.title
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Text(
                text =
                    article.sourceName
            )
        }
    }
}