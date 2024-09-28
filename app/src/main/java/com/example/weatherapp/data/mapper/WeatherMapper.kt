package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.model.CityDto
import com.example.weatherapp.data.model.CityEntity
import com.example.weatherapp.data.model.WeatherDto
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.Weather

interface WeatherMapper {
    fun mapCity(cityDto: CityDto): List<City>

    fun mapWeather(weatherDto: WeatherDto): Weather

    fun mapCityToEntity(city: City): CityEntity

    fun mapEntityToCity(cityEntity: CityEntity): City
}