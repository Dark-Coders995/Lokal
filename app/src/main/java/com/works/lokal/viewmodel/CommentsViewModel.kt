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

    init {
        getResponses()
    }

    private fun getResponses() {

        _responsesUIState.value =
            _responsesUIState.value.copy(isLoading = true, responses = emptyList())

        viewModelScope.launch {
            when (val result = repository.getResponses()) {

                is NetworkResult.Success -> {
                    _responsesUIState.update {
                        it.copy(
                            responses = result.data,
                            isLoading = false,
                        )
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
        }
    }
}