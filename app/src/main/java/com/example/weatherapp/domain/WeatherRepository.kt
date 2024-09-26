package com.example.weatherapp.domain

import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.Weather

interface WeatherRepository {
    suspend fun searchCity(city: String): List<City>

    suspend fun checkWeather(latitude: Double, longitude: Double): Weather
}