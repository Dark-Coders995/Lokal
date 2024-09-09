package com.works.lokal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.works.lokal.screens.BookMarkScreen
import com.works.lokal.screens.DetailsScreen
import com.works.lokal.screens.MainContent

@Composable
fun Navigation(navController: NavHostController , modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = JobsScreens.HomeScreen.name,
        modifier = modifier
    ) {
        composable(JobsScreens.HomeScreen.name) {
            MainContent(navController = navController)
        }
        composable(JobsScreens.DetailScreen.name) {
            DetailsScreen(navController = navController)
        }
        composable(JobsScreens.BookMarkScreen.name) {
            BookMarkScreen(navController = navController)
        }
    }
}