package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.data.mapper.WeatherMapper
import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.Weather

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val mapper: WeatherMapper
) : WeatherRepository {
    override suspend fun searchCity(city: String): List<City> {
        return mapper.mapCity(weatherApi.searchCity(city = city))
    }

    override suspend fun checkWeather(latitude: Double, longitude: Double): Weather {
        return mapper.mapWeather(
            weatherApi.checkWeather(
                latitude = latitude,
                longitude = longitude
            )
        )
    }
}