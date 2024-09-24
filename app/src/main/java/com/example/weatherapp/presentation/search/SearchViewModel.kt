package com.example.weatherapp.presentation.search

import androidx.lifecycle.ViewModel
import com.example.weatherapp.domain.SearchRepository
import com.example.weatherapp.presentation.search.model.SearchSideEffect
import com.example.weatherapp.presentation.search.model.SearchState
import kotlinx.coroutines.CoroutineExceptionHandler
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SearchViewModel(private val searchRepository: SearchRepository) :
    ContainerHost<SearchState, SearchSideEffect>, ViewModel() {

    override val container = container<SearchState, SearchSideEffect>(SearchState.Loading)

    private val errorHandler by lazy {
        CoroutineExceptionHandler { _, _ ->
            intent {
                reduce {
                    SearchState.Error
                }
            }
        }
    }

    init {
        loadCoinList()
    }

    fun loadCoinList() {
        intent {
            reduce {
                SearchState.Content(emptyList())
            }
        }
    }
}