package com.example.newsapp.feature_news.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.newsapp.feature_news.presentation.bookmarks.BookmarksScreen
import com.example.newsapp.feature_news.presentation.detail.DetailScreen
import com.example.newsapp.feature_news.presentation.home.HomeScreen
import com.example.newsapp.feature_news.presentation.search.SearchScreen

@Composable
fun NewsApp() {

    val navController =
        rememberNavController()

    Scaffold(

        bottomBar = {

            NavigationBar {

                val current =
                    navController
                        .currentBackStackEntryAsState()
                        .value
                        ?.destination
                        ?.route

                bottomNavItems
                    .forEach { item ->

                        NavigationBarItem(

                            selected =
                                current ==
                                        item.route,

                            onClick = {

                                navController.navigate(
                                    item.route
                                )
                            },

                            icon = {

                                Icon(
                                    item.icon,
                                    null
                                )
                            },

                            label = {

                                Text(
                                    item.title
                                )
                            }
                        )
                    }
            }
        }

    ) { padding ->

        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = Routes.Home.route
        ) {

            composable(
                Routes.Home.route
            ) {
                HomeScreen(
                    navController
                )
            }

            composable(
                Routes.Search.route
            ) {
                SearchScreen(
                    navController
                )
            }

            composable(
                Routes.Bookmarks.route
            ) {
                BookmarksScreen(
                    navController
                )
            }

            composable(
                Routes.Detail.route
            ) {
                DetailScreen()
            }
        }
    }
}