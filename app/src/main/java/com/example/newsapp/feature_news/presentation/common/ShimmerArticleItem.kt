package com.example.newsapp.feature_news.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun ShimmerArticleItem() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .background(
                        Color.LightGray,
                        RoundedCornerShape(8.dp)
                    )
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(18.dp)
                    .background(
                        Color.LightGray,
                        RoundedCornerShape(8.dp)
                    )
            )
        }
    }
}