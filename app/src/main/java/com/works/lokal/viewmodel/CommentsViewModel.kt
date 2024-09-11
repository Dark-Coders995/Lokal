package com.works.lokal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.works.lokal.models.Response
import com.works.lokal.network.NetworkResult
import com.works.lokal.repository.ResponsesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ResponsesUIState(
    val responses: List<Response> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false
)

class ResponsesViewModel(
    private val repository: ResponsesRepository
) : ViewModel() {

    private val _responsesUIState = MutableStateFlow(ResponsesUIState())
    val responsesUIState: StateFlow<ResponsesUIState> = _responsesUIState.asStateFlow()

    private var currentPage = 1
    private val pageSize = 20
    private var endReached = false
    private var isLoading = false
    init {
        getResponses()
    }

    fun canLoadMore(): Boolean {
        return !endReached && !isLoading
    }


    fun getResponses() {
        if (isLoading || endReached) return

        isLoading = true
        _responsesUIState.value =
            _responsesUIState.value.copy(isLoading = true)

        viewModelScope.launch {
            when (val result = repository.getResponses(currentPage, pageSize)) {

                is NetworkResult.Success -> {
                    val newResponses = result.data
                    if (newResponses.isNotEmpty()) {
                        currentPage++
                        _responsesUIState.update {
                            it.copy(
                                responses = it.responses + newResponses,
                                isLoading = false
                            )
                        }
                    } else {
                        endReached = true
                        _responsesUIState.update {
                            it.copy(isLoading = false)
                        }
                    }
                }

                is NetworkResult.Error -> {
                    _responsesUIState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
            isLoading = false
        }
    }
}