package com.example.newsapp.feature_news.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(
    viewModel:
    DetailViewModel =
        hiltViewModel()
) {

    val uiState by
    viewModel.uiState.collectAsState()

    val article =
        uiState.article

    Column(
        modifier =
            Modifier.padding(
                16.dp
            )
    ) {

        Text(
            text =
                article?.title.orEmpty()
        )

        Spacer(
            modifier =
                Modifier.height(
                    12.dp
                )
        )

        Text(
            text =
                article?.description
                    .orEmpty()
        )
    }
}