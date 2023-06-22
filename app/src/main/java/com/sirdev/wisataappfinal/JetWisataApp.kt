package com.sirdev.wisataappfinal

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sirdev.wisataappfinal.navigation.Screen
import com.sirdev.wisataappfinal.ui.component.BottomBar
import com.sirdev.wisataappfinal.ui.component.TopHomeBar
import com.sirdev.wisataappfinal.ui.screen.aboutpage.AboutPageScreen
import com.sirdev.wisataappfinal.ui.screen.detail.DetailScreen
import com.sirdev.wisataappfinal.ui.screen.home.HomeScreen

@Composable
fun JetWisataApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val topBarState = rememberSaveable { (mutableStateOf(true)) }

    when (currentRoute) {
        Screen.Home.route -> {
            topBarState.value = true
        }
        Screen.DetailWisata.route -> {
            topBarState.value = false
        }
        Screen.About.route -> {
            topBarState.value = false
        }
    }
    Scaffold(
        topBar = {
            TopHomeBar(
                topBarState = topBarState
            )
        },
        bottomBar = {
            if (currentRoute != Screen.DetailWisata.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) {  innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { wisataId ->
                        navController.navigate(Screen.DetailWisata.createRoute(wisataId))
                    }
                )
            }
            composable(Screen.About.route){
                AboutPageScreen()
            }
            composable(
                route = Screen.DetailWisata.route,
                arguments = listOf(navArgument("wisataId") {type = NavType.LongType}),
            ) {
                val id = it.arguments?.getLong("wisataId") ?: -1L
                DetailScreen(
                    wisataId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun JetWisataAppPreview() {
    JetWisataApp()
}