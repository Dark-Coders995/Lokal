package com.works.lokal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable


import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.works.lokal.screens.ResponsesListScreen

import com.works.lokal.ui.theme.LokalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                val navController = rememberNavController()
                ResponsesListScreen(navController)
            }
        }
    }
}

@Composable
fun MyApp(content : @Composable () -> Unit) {
    LokalTheme {
        content()
    }
}

@Preview(showBackground =  true)
@Composable
fun DefaultPreview() {
    MyApp{
        val navController = rememberNavController()
        ResponsesListScreen(navController)
    }
}