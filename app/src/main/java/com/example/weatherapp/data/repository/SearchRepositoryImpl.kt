package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.domain.SearchRepository

class SearchRepositoryImpl(val weatherApi: WeatherApi) : SearchRepository {
}