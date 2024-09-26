package com.example.weatherapp.presentation.detail

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.presentation.detail.model.DetailUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    cityName: String,
    latitude: Float,
    longitude: Float,
    viewModel: DetailViewModel = koinViewModel()
) {
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle()

    when (val stateValue = state.value.detailUiState) {
        DetailUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is DetailUiState.Content -> {

        }

        DetailUiState.Error -> {

        }
    }

    LaunchedEffect(true) {
        viewModel.loadWeatherInfo(latitude, longitude)
    }
}