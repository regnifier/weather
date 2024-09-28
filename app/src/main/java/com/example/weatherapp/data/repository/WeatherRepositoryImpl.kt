package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.CityDao
import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.data.mapper.WeatherMapper
import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val weatherApi: WeatherApi,
    private val cityDao: CityDao,
    private val mapper: WeatherMapper
) : WeatherRepository {
    override suspend fun searchCity(city: String): List<City> =
        withContext(ioDispatcher) {
            mapper.mapCity(weatherApi.searchCity(city = city))
        }

    override suspend fun checkWeather(latitude: Double, longitude: Double): Weather =
        withContext(ioDispatcher) {
            mapper.mapWeather(
                weatherApi.checkWeather(
                    latitude = latitude,
                    longitude = longitude
                )
            )
        }

    override suspend fun saveCity(city: City) = withContext(ioDispatcher) {
        cityDao.insertCity(mapper.mapCityToEntity(city))
    }

    override suspend fun getSavedCities(): List<City> = withContext(ioDispatcher) {
        cityDao.getAll().map { mapper.mapEntityToCity(it) }
    }
}