package com.example.newsapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(

    val route: String,

    val title: String,

    val icon: ImageVector
)

val bottomNavItems =
    listOf(

        BottomNavItem(
            Routes.Home.route,
            "Home",
            Icons.Default.Home
        ),

        BottomNavItem(
            Routes.Search.route,
            "Search",
            Icons.Default.Search
        ),

        BottomNavItem(
            Routes.Bookmarks.route,
            "Bookmarks",
            Icons.Default.Favorite
        )
    )