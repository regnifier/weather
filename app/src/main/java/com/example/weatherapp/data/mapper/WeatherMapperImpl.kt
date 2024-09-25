package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.model.CityDto
import com.example.weatherapp.domain.model.City

class WeatherMapperImpl : WeatherMapper {
    override fun mapCity(cityDto: CityDto): List<City> =
        cityDto.results?.map {
            City(
                cityName = it.name ?: throw IllegalArgumentException(),
                countryCode = it.country ?: throw IllegalArgumentException()
            )
        } ?: emptyList()
}