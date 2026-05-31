package com.example.newsapp.feature_news.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel =
        hiltViewModel()
) {

    val context =
        LocalContext.current

    val uiState by
    viewModel.uiState.collectAsState()

    val article =
        uiState.article

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text("Article")
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            Icons.Default.ArrowBack,
                            null
                        )
                    }
                },

                actions = {

                    IconButton(
                        onClick = {
                            viewModel.toggleBookmark()
                        }
                    ) {

                        Icon(
                            Icons.Default.Bookmark,
                            null
                        )
                    }

                    IconButton(
                        onClick = {

                            article?.url?.let {

                                context.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(it)
                                    )
                                )
                            }
                        }
                    ) {

                        Icon(
                            Icons.Default.OpenInBrowser,
                            null
                        )
                    }

                    IconButton(
                        onClick = {

                            article?.url?.let {

                                val share =
                                    Intent(
                                        Intent.ACTION_SEND
                                    ).apply {

                                        type = "text/plain"

                                        putExtra(
                                            Intent.EXTRA_TEXT,
                                            it
                                        )
                                    }

                                context.startActivity(
                                    Intent.createChooser(
                                        share,
                                        "Share article"
                                    )
                                )
                            }
                        }
                    ) {

                        Icon(
                            Icons.Default.Share,
                            null
                        )
                    }
                }
            )
        }

    ) { padding ->

        Column(
            modifier =
                Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
        ) {

            Text(
                text =
                    article?.title
                        .orEmpty(),

                style =
                    MaterialTheme
                        .typography
                        .headlineSmall
            )

            Spacer(
                modifier =
                    Modifier.height(
                        12.dp
                    )
            )

            Text(
                text =
                    article?.sourceName
                        .orEmpty(),

                style =
                    MaterialTheme
                        .typography
                        .bodyMedium
            )

            Spacer(
                modifier =
                    Modifier.height(
                        16.dp
                    )
            )

            Text(
                text =
                    article?.description
                        .orEmpty()
            )

            Spacer(
                modifier =
                    Modifier.height(
                        16.dp
                    )
            )

            Text(
                text =
                    article?.content
                        .orEmpty()
            )
        }
    }
}