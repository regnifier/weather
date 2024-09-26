package com.example.weatherapp.presentation.search.model

import androidx.compose.runtime.Immutable
import com.example.weatherapp.domain.model.City

data class SearchState(
    val searchUiState: SearchUiState = SearchUiState.Loading,
    val searchText: String = ""
)

sealed class SearchUiState {
    data object Loading : SearchUiState()

    @Immutable
    data class Content(
        val cityList: List<City>
    ) : SearchUiState()

    data object Error : SearchUiState()
}