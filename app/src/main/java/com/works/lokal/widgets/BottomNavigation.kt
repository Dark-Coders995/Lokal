package com.works.lokal.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.works.lokal.navigation.JobsScreens

@Composable
fun BottomNavigationBar(navController: NavController) {
    var selectedItem by rememberSaveable { mutableStateOf(false) }

    NavigationBar {
        NavigationBarItem(
            alwaysShowLabel = true,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "HomePage"
                )
            },
            selected = selectedItem,
            label = {
                Text(
                    text = "Home"
                )
            },
            onClick = {
                selectedItem = true
                navController.navigate(route = JobsScreens.HomeScreen.name)
            }
        )
        NavigationBarItem(
                alwaysShowLabel = true,
        icon = {
            Icon(
                imageVector =  Icons.Filled.Favorite,
                contentDescription = "Bookmarks"
                )
        },
        selected = selectedItem,
        label = {
            Text(text = "Bookmarks")
        },
        onClick = {
            selectedItem = true
            navController.navigate(route = JobsScreens.HomeScreen.name)
        }
        )
        /*
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.path) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }*/
    }
}