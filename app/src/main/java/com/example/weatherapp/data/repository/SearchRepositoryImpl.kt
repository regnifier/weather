package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.data.mapper.WeatherMapper
import com.example.weatherapp.domain.SearchRepository
import com.example.weatherapp.domain.model.City

class SearchRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val mapper: WeatherMapper
) : SearchRepository {
    override suspend fun searchCity(city: String): List<City> {
        return mapper.mapCity(weatherApi.searchCity(city = city))
    }
}