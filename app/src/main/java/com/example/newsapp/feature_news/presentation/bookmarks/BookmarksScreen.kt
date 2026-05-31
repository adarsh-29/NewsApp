package com.example.newsapp.feature_news.presentation.bookmarks

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.feature_news.presentation.common.ArticleItem
import com.example.newsapp.feature_news.presentation.common.EmptyState
import com.example.newsapp.navigation.Routes
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun BookmarksScreen(
    navController: NavController,
    viewModel:
    BookmarksViewModel = hiltViewModel())
{

    val uiState by viewModel.uiState.collectAsState()
    if (
        uiState.articles.isEmpty()
    ) {

        EmptyState(
            "No bookmarks yet"
        )

    }
    else{

        LazyColumn {

            items(
                uiState.articles
            ) { article ->

                ArticleItem(
                    article =
                        article,
                    onClick = {

                        val encoded =
                            URLEncoder.encode(
                                article.url,
                                StandardCharsets.UTF_8.toString()
                            )

                        navController.navigate(
                            Routes.Detail.create(
                                encoded
                            )
                        )
                    }
                )
            }
        }
    }

}