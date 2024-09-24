package com.example.weatherapp.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.presentation.search.model.SearchState
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel()
) {
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle()

    when (state.value) {
        SearchState.Loading -> {

        }
        is SearchState.Content -> {
            Box(Modifier.fillMaxSize()) {
                Text(text = "test", modifier = Modifier.align(Alignment.Center))
            }
        }
        SearchState.Error -> {

        }
    }
}