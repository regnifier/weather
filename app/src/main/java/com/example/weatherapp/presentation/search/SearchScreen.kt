package com.example.weatherapp.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.presentation.search.model.SearchUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    onNavigateToDetailScreen: (
        cityName: String,
        latitude: Float,
        longitude: Float
    ) -> Unit
) {
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = state.value.searchText,
            onValueChange = viewModel::search,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        when (val stateValue = state.value.searchUiState) {
            SearchUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            is SearchUiState.Content -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(stateValue.cityList) { city ->
                        Text(
                            text = "${city.cityName} |${city.countryCode}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .clickable {
                                    onNavigateToDetailScreen(
                                        city.cityName,
                                        52.2298f,
                                        21.0118f
                                    )
                                }
                        )
                    }
                }
            }

            SearchUiState.Error -> {

            }
        }
    }
}