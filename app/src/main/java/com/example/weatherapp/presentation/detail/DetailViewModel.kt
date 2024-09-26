package com.example.weatherapp.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.presentation.detail.model.DetailSideEffect
import com.example.weatherapp.presentation.detail.model.DetailState
import com.example.weatherapp.presentation.detail.model.DetailUiState
import com.example.weatherapp.utils.PRESSURE_MSL
import com.example.weatherapp.utils.RAIN
import com.example.weatherapp.utils.RELATIVE_HUMIDITY_2M
import com.example.weatherapp.utils.TEMPERATURE_2M
import com.example.weatherapp.utils.WEATHER_CODE
import com.example.weatherapp.utils.WIND_DIRECTION_10M
import com.example.weatherapp.utils.WIND_GUSTS_10M
import com.example.weatherapp.utils.WIND_SPEED_10M
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container


class DetailViewModel(private val weatherRepository: WeatherRepository) :
    ContainerHost<DetailState, DetailSideEffect>, ViewModel() {

    override val container = container<DetailState, DetailSideEffect>(DetailState())

    private val errorHandler by lazy {
        CoroutineExceptionHandler { _, _ ->
            intent {
                reduce {
                    state.copy(detailUiState = DetailUiState.Error)
                }
            }
        }
    }


    fun loadWeatherInfo(latitude: Float, longitude: Float) {
        intent {
            reduce {
                state.copy(detailUiState = DetailUiState.Loading)
            }
        }
        viewModelScope.launch(errorHandler) {
            val weatherInfo = weatherRepository.checkWeather(
                latitude = latitude.toDouble(),
                longitude = longitude.toDouble()
            )
            intent {
                reduce {
                    state.copy(detailUiState = DetailUiState.Content(weatherInfo))
                }
            }
        }
    }
}