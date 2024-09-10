package com.works.lokal.di

import com.works.lokal.network.ResponsesAPIService
import com.works.lokal.repository.ResponsesRepository
import com.works.lokal.repository.ResponsesRepositoryImpl
import com.works.lokal.viewmodel.ResponsesViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val appModules = module {
    single<ResponsesAPIService> { ResponsesAPIService() }
    single { Dispatchers.IO }
    single<ResponsesRepository> {
        ResponsesRepositoryImpl(
            apiService = get(),
            dispatcher = get()
        )
    }
    single { ResponsesViewModel(repository = get()) }

}
