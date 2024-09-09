package com.works.lokal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.works.lokal.screens.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = JobsScreens.HomeScreen.name){
        composable(JobsScreens.HomeScreen.name){
            HomeScreen(navController = navController)

        }
        composable(JobsScreens.DetailScreen.name){

        }
    }
}