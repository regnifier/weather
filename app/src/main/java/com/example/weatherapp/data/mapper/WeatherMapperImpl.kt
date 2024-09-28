package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.model.CityDto
import com.example.weatherapp.data.model.CityEntity
import com.example.weatherapp.data.model.WeatherDto
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.Current
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.utils.getLocalDateFromString

class WeatherMapperImpl : WeatherMapper {
    override fun mapCity(cityDto: CityDto): List<City> =
        cityDto.results?.map {
            City(
                cityName = it.name ?: throw IllegalArgumentException(),
                countryCode = it.country ?: throw IllegalArgumentException(),
                latitude = it.latitude ?: throw IllegalArgumentException(),
                longitude = it.longitude ?: throw IllegalArgumentException(),
                id = it.id ?: throw IllegalArgumentException()
            )
        } ?: emptyList()

    override fun mapWeather(weatherDto: WeatherDto): Weather = with(weatherDto) {
        Weather(
            current = Current(
                pressureMsl = current?.pressureMsl ?: throw IllegalArgumentException(),
                relativeHumidity2m = current.relativeHumidity2m ?: throw IllegalArgumentException(),
                temperature2m = current.temperature2m ?: throw IllegalArgumentException(),
                time = current.time?.getLocalDateFromString() ?: throw IllegalArgumentException(),
                windGusts10m = current.windGusts10m ?: throw IllegalArgumentException(),
                windSpeed10m = current.windSpeed10m ?: throw IllegalArgumentException()
            ),
            dailyInfo = daily?.time?.let { dailyTime ->
                daily.temperature2mMax?.let { dailyTemperature ->
                    dailyTime.zip(dailyTemperature)
                } ?: throw IllegalArgumentException()
            } ?: throw IllegalArgumentException(),
            dailyTemperatureUnit = dailyUnits?.temperature2mMax ?: throw IllegalArgumentException(),
            hourlyInfo = hourly?.time?.let { hourlyTime ->
                hourly.temperature2m?.let { hourlyTemperature ->
                    hourlyTime.zip(hourlyTemperature)
                } ?: throw IllegalArgumentException()
            } ?: throw IllegalArgumentException(),
            hourlyTemperatureUnits = hourlyUnits?.temperature2m ?: throw IllegalArgumentException()
        )
    }

    override fun mapCityToEntity(city: City): CityEntity = with(city) {
        CityEntity(
            id = id,
            cityName = cityName,
            countryCode = countryCode,
            latitude = latitude,
            longitude = longitude
        )
    }

    override fun mapEntityToCity(cityEntity: CityEntity): City = with(cityEntity) {
        City(
            id = id,
            cityName = cityName,
            countryCode = countryCode,
            latitude = latitude,
            longitude = longitude
        )
    }
}