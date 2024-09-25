package com.example.weatherapp.domain

import com.example.weatherapp.domain.model.City

interface SearchRepository {
    suspend fun searchCity(city: String): List<City>
}