package com.example.weatherapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.SearchRepository
import com.example.weatherapp.presentation.search.model.SearchSideEffect
import com.example.weatherapp.presentation.search.model.SearchState
import com.example.weatherapp.presentation.search.model.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SearchViewModel(private val searchRepository: SearchRepository) :
    ContainerHost<SearchState, SearchSideEffect>, ViewModel() {

    override val container = container<SearchState, SearchSideEffect>(SearchState())

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
                        state.copy(uiState = UiState.Loading)
                    }
                }
                val searchedCities = searchRepository.searchCity(query)
                intent {
                    reduce {
                        state.copy(uiState = UiState.Content(searchedCities))
                    }
                }
            }
            .flowOn(Dispatchers.IO)
            .catch {
                intent {
                    reduce {
                        state.copy(uiState = UiState.Error)
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}