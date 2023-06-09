package com.sirdev.wisataappfinal.navigation

sealed class Screen (val route: String) {
    object Home : Screen("home")
    object About : Screen("about_page")
    object DetailWisata : Screen("home/{wisataId}") {
        fun createRoute(wisataId: Long) = "home/$wisataId"
    }
}
