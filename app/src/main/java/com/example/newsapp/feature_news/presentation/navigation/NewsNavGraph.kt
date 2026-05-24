package com.example.newsapp.feature_news.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.newsapp.feature_news.presentation.bookmarks.BookmarksScreen
import com.example.newsapp.feature_news.presentation.detail.DetailScreen
import com.example.newsapp.feature_news.presentation.home.HomeScreen
import com.example.newsapp.feature_news.presentation.search.SearchScreen

@Composable
fun NewsNavGraph() {

    val navController =
        rememberNavController()

    NavHost(
        navController =
            navController,
        startDestination =
            Routes.Home.route
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
            route =
                Routes.Detail.route,

            arguments =
                listOf(
                    navArgument(
                        "url"
                    ) {
                        type =
                            NavType.StringType
                    }
                )
        ) {

            DetailScreen()
        }
    }
}