package com.example.weatherapp.presentation.search.model

sealed class SearchState {
    data object Loading : SearchState()

    data class Content(val cityList: List<String>) : SearchState()

    data object Error : SearchState()
}