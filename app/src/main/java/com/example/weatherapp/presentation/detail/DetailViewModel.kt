package com.example.weatherapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.presentation.detail.model.DetailState
import com.example.weatherapp.presentation.detail.model.DetailUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class DetailViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _detailState = MutableStateFlow(DetailState())
    val detailState get() = _detailState.asStateFlow()

    private fun currentState(): DetailState = _detailState.value

    private fun updateState(newState: DetailState) {
        _detailState.value = newState
    }

    private val errorHandler by lazy {
        CoroutineExceptionHandler { _, _ ->
            updateState(currentState().copy(detailUiState = DetailUiState.Error))
        }
    }

    fun loadWeatherInfo(latitude: Float, longitude: Float, cityName: String) {
        viewModelScope.launch(errorHandler) {
            updateState(currentState().copy(detailUiState = DetailUiState.Loading))
            val weatherInfo = weatherRepository.checkWeather(
                latitude = latitude.toDouble(),
                longitude = longitude.toDouble()
            )
            updateState(
                currentState().copy(
                    detailUiState = DetailUiState.Content(
                        cityName = cityName,
                        weather = weatherInfo
                    )
                )
            )
        }
    }
}