package com.works.lokal.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.works.lokal.viewmodel.ResponsesViewModel
import com.works.lokal.widgets.ResponseItem
import org.koin.androidx.compose.koinViewModel



@Composable
fun HomeScreen(modifier: Modifier) {
    val viewModel: ResponsesViewModel = koinViewModel()
    val responsesUIState by viewModel.responsesUIState.collectAsStateWithLifecycle()
    Column(
        modifier
            .fillMaxSize()
            .padding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(visible = responsesUIState.responses.isNotEmpty()) {
            LazyColumn {
                items(items = responsesUIState.responses) { response ->
                    ResponseItem(response = response)
                }
            }
        }

        AnimatedVisibility(visible = responsesUIState.isLoading) {
            CircularProgressIndicator()
        }

        AnimatedVisibility(visible = responsesUIState.error != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Error",
                    tint = Color.Red,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(bottom = 10.dp)
                )
                Text(
                    text = "There Was An Error! Try Again...",
                    color = Color.Red,
                    fontSize = 24.sp
                )
            }
        }
    }
}