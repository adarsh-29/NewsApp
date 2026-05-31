package com.example.newsapp.feature_news.presentation.search


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.feature_news.presentation.common.*
import com.example.newsapp.navigation.Routes
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel:
    SearchViewModel =
        hiltViewModel()
) {

    val uiState by
    viewModel.uiState.collectAsState()

    val pagingItems =
        viewModel
            .pagingFlow
            .collectAsLazyPagingItems()

    Column {

        OutlinedTextField(
            value =
                uiState.query,

            onValueChange =
                viewModel::onQueryChange,

            modifier =
                Modifier.fillMaxWidth(),

            label = {
                Text("Search")
            }
        )

        when (

            val refresh =
                pagingItems.loadState.refresh

        ) {

            is LoadState.Loading -> {

                LazyColumn {

                    items(5) {
                        ShimmerArticleItem()
                    }
                }
            }

            is LoadState.Error -> {

                PagingError(
                    refresh.error.message
                        ?: "Error"
                ) {

                    pagingItems.retry()
                }
            }

            else -> {

                if (
                    pagingItems.itemCount == 0
                    &&
                    uiState.query
                        .isNotBlank()
                ) {

                    EmptyState(
                        "No results"
                    )

                } else {

                    LazyColumn {

                        items(
                            pagingItems.itemCount
                        ) { index ->

                            pagingItems[index]
                                ?.let { article ->

                                    ArticleItem(
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
            }
        }
    }
}