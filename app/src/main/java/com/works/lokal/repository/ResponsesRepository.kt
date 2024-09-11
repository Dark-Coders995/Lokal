package com.works.lokal.repository

import com.works.lokal.models.Response
import com.works.lokal.network.NetworkResult
import com.works.lokal.network.ResponsesAPIService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface ResponsesRepository {
    suspend fun getResponses(page: Int, limit: Int): NetworkResult<List<Response>>
}

class ResponsesRepositoryImpl(
    private val apiService: ResponsesAPIService,
    private val dispatcher: CoroutineDispatcher
) : ResponsesRepository {

    override suspend fun getResponses(page: Int, limit: Int): NetworkResult<List<Response>> {
        return withContext(dispatcher) {
            try {
                val response = apiService.getResponses(page, limit)
                NetworkResult.Success(response)
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Something went wrong")
            }
        }
    }
}