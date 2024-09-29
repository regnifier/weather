package com.example.weatherapp.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.R
import com.example.weatherapp.presentation.detail.model.DetailUiState
import com.example.weatherapp.presentation.detail.ui.DetailScreenContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    cityName: String,
    latitude: Float,
    longitude: Float,
    viewModel: DetailViewModel = koinViewModel()
) {
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle()

    when (val stateValue = state.value.detailUiState) {
        DetailUiState.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is DetailUiState.Content -> {
            DetailScreenContent(
                modifier = modifier,
                weatherInfo = stateValue.weather,
                cityName = stateValue.cityName
            )
        }

        DetailUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(R.string.search_error),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.loadWeatherInfo(latitude, longitude, cityName)
    }
}