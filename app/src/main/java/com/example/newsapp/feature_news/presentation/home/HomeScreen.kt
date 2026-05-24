package com.example.newsapp.feature_news.presentation.home

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
import com.example.newsapp.feature_news.presentation.navigation.Routes
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel =
        hiltViewModel()
) {


    val pagingItems = viewModel
            .pagingFlow
            .collectAsLazyPagingItems()

    val refreshing =
        pagingItems.loadState.refresh is LoadState.Loading

    val pullRefreshState =
        rememberPullToRefreshState()

    Box(
        modifier =
            Modifier.pullToRefresh(
                state =
                    pullRefreshState,

                isRefreshing =
                    refreshing,

                onRefresh = {
                    pagingItems.refresh()
                }
            )
    ) {

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
                    message =
                        refresh.error.message
                            ?: "Error",
                    onRetry = {
                        pagingItems.retry()
                    }
                )
            }

            else -> {

                if (
                    pagingItems.itemCount == 0
                ) {

                    EmptyState(
                        "No news found"
                    )

                } else {

                    LazyColumn {

                        items(
                            pagingItems.itemCount
                        ) { index ->

                            pagingItems[index]
                                ?.let { article ->

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
            }
        }

        /*PullToRefreshBox(state = pullRefreshState) {
            CircularProgressIndicator()
        }*/
    }
}