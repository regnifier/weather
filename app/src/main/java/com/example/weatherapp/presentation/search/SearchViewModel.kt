package com.example.weatherapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.presentation.search.model.SearchState
import com.example.weatherapp.presentation.search.model.SearchUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _searchState = MutableStateFlow(SearchState())
    val searchState get() = _searchState.asStateFlow()

    private fun currentState(): SearchState = _searchState.value

    private fun updateState(newState: SearchState) {
        _searchState.value = newState
    }

    private val errorHandler by lazy {
        CoroutineExceptionHandler { _, _ ->
            updateState(currentState().copy(searchUiState = SearchUiState.Error))
        }
    }

    fun saveCity(city: City) {
        viewModelScope.launch(errorHandler) {
            weatherRepository.saveCity(city)
        }
    }

    fun loadSavedCities() {
        viewModelScope.launch(errorHandler) {
            updateState(currentState().copy(searchUiState = SearchUiState.Loading))
            val savedCities = weatherRepository.getSavedCities()
            updateState(
                currentState().copy(
                    searchUiState = SearchUiState.Content,
                    savedCities = savedCities
                )
            )
        }
    }

    @OptIn(FlowPreview::class)
    fun search(query: String) {
        updateState(currentState().copy(searchText = query))
        MutableStateFlow(query)
            .debounce(1000L)
            .onEach {
                updateState(currentState().copy(searchUiState = SearchUiState.Loading))
                val searchedCities = weatherRepository.searchCity(query)
                updateState(
                    currentState().copy(
                        searchUiState = SearchUiState.Content,
                        cityList = searchedCities
                    )
                )
            }
            .flowOn(Dispatchers.IO)
            .catch {
                updateState(currentState().copy(searchUiState = SearchUiState.Error))
            }
            .launchIn(viewModelScope)
    }
}