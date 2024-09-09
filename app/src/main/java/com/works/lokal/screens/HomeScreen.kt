package com.works.lokal.screens


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.works.lokal.navigation.JobsScreens
import com.works.lokal.navigation.Navigation
import com.works.lokal.widgets.BottomNavigationBar
import com.works.lokal.widgets.JobRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Movies"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Magenta),
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp
                    )
            )
        },
        bottomBar = {
            BottomAppBar { BottomNavigationBar(navController = navController) }
        }
    ) {
        Navigation(navController , modifier = Modifier.padding(it))
    }
}


@Composable
fun MainContent(
    navController: NavController,
    movieList: List<String> = listOf(
        "Avatar",
        "300",
        "Harry Potter",
        "Life",
        "Love of Life",
        "Yes"
    )
) {
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        LazyColumn {
            items(
                items = movieList
            ) {
                JobRow(job = it) { job ->
                    navController.navigate(route = JobsScreens.DetailScreen.name)
                    Log.d("TAG", "Movie Content $job")
                }
            }
        }
    }
}