package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.model.CityDto
import com.example.weatherapp.domain.model.City

interface WeatherMapper {
    fun mapCity(cityDto: CityDto): List<City>
}