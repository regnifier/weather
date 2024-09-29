package com.example.weatherapp.presentation.search.model

import androidx.compose.runtime.Immutable
import com.example.weatherapp.domain.model.City

@Immutable
data class SearchState(
    val searchUiState: SearchUiState = SearchUiState.Loading,
    val searchText: String = "",
    val savedCities: List<City> = emptyList(),
    val cityList: List<City> = emptyList()
)

sealed class SearchUiState {
    data object Loading : SearchUiState()

    data object Content : SearchUiState()

    data object Error : SearchUiState()
}