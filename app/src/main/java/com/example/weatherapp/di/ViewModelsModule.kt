package com.example.weatherapp.di

import com.example.weatherapp.presentation.detail.DetailViewModel
import com.example.weatherapp.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { SearchViewModel(weatherRepository = get()) }

    viewModel { DetailViewModel(weatherRepository = get()) }
}