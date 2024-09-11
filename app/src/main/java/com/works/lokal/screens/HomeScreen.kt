package com.works.lokal.screens

import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState

import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf

import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

import com.works.lokal.viewmodel.ResponsesViewModel
import com.works.lokal.widgets.ResponseItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun PaginatedResponseList(viewModel: ResponsesViewModel, modifier: Modifier) {
    val uiState = viewModel.responsesUIState.collectAsState().value
    val listState = rememberLazyListState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding()
    ) {
        LazyColumn(state = listState) {
            items(uiState.responses) { response ->
                ResponseItem(response)
            }
            if (uiState.isLoading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }

        uiState.error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }

        if (uiState.responses.isNotEmpty() && !uiState.isLoading && uiState.error == null && !viewModel.canLoadMore()) {
            Text(
                text = "End of list",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        InfiniteListHandler(listState = listState) {
            viewModel.getResponses()
        }
    }
}

@Composable
fun InfiniteListHandler(listState: LazyListState, loadMore: () -> Unit) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem != null && lastVisibleItem.index >= listState.layoutInfo.totalItemsCount - 5
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            loadMore()
        }
    }
}


@Composable
fun HomeScreen(modifier: Modifier) {
    val viewModel: ResponsesViewModel = koinViewModel()
    PaginatedResponseList(viewModel = viewModel, modifier = modifier)
}