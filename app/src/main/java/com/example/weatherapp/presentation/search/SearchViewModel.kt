package com.example.weatherapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.presentation.search.model.SearchSideEffect
import com.example.weatherapp.presentation.search.model.SearchState
import com.example.weatherapp.presentation.search.model.SearchUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SearchViewModel(private val weatherRepository: WeatherRepository) :
    ContainerHost<SearchState, SearchSideEffect>, ViewModel() {

    override val container = container<SearchState, SearchSideEffect>(SearchState())

    private val errorHandler by lazy {
        CoroutineExceptionHandler { _, _ ->
            intent {
                reduce {
                    state.copy(searchUiState = SearchUiState.Error)
                }
            }
        }
    }

    fun saveCity(city: City) {
        viewModelScope.launch(errorHandler) {
            weatherRepository.saveCity(city)
        }
    }

    fun loadSavedCities() {
        viewModelScope.launch(errorHandler) {
            intent {
                reduce {
                    state.copy(searchUiState = SearchUiState.Loading)
                }
            }
            val savedCities = weatherRepository.getSavedCities()
            intent {
                reduce {
                    state.copy(searchUiState = SearchUiState.Content, savedCities = savedCities)
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    fun search(query: String) {
        intent {
            reduce {
                state.copy(searchText = query)
            }
        }
        MutableStateFlow(query)
            .debounce(1000L)
            .onEach {
                intent {
                    reduce {
                        state.copy(searchUiState = SearchUiState.Loading)
                    }
                }
                val searchedCities = weatherRepository.searchCity(query)
                intent {
                    reduce {
                        state.copy(searchUiState = SearchUiState.Content, cityList = searchedCities)
                    }
                }
            }
            .flowOn(Dispatchers.IO)
            .catch {
                intent {
                    reduce {
                        state.copy(searchUiState = SearchUiState.Error)
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}