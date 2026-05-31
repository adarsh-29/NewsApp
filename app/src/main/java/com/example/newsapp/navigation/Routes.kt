package com.example.newsapp.navigation

sealed class Routes(
    val route: String
) {

    data object Home :
        Routes("home")

    data object Search :
        Routes("search")

    data object Bookmarks :
        Routes("bookmarks")

    data object Detail :
        Routes("detail/{url}") {

        fun create(
            url: String
        ) = "detail/$url"
    }
}