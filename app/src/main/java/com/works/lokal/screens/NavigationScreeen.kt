package com.works.lokal.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.works.lokal.nav.NavItem

@Composable
fun NavigationScreens(navController: NavHostController, modifier: Modifier) {
    NavHost(navController, startDestination = NavItem.Home.path) {
        composable(NavItem.Home.path) { HomeScreen(modifier) }
        composable(NavItem.BookMark.path) { BookMarkScreen(modifier) }
    }
}