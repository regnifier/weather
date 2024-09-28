package com.example.weatherapp.presentation.detail.model

import com.example.weatherapp.domain.model.Weather

data class DetailState(
    val detailUiState: DetailUiState = DetailUiState.Loading
)

sealed class DetailUiState {
    data object Loading : DetailUiState()

    data class Content(
        val cityName: String,
        val weather: Weather
    ) : DetailUiState()

    data object Error : DetailUiState()
}