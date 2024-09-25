package com.example.weatherapp.presentation.search.model

import com.example.weatherapp.domain.model.City

data class SearchState(
    val uiState: UiState = UiState.Loading,
    val searchText: String = ""
)

sealed class UiState {
    data object Loading : UiState()

    data class Content(
        val cityList: List<City>
    ) : UiState()

    data object Error : UiState()
}