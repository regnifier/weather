package com.example.weatherapp.domain.model

data class Weather(
    val current: Current,
    val dailyInfo: List<Pair<String, Double>>,
    val dailyTemperatureUnit: String,
    val hourlyInfo: List<Pair<String, Double>>,
    val hourlyTemperatureUnits: String
)

data class Current(
    val pressureMsl: Double,
    val relativeHumidity2m: Int,
    val temperature2m: Double,
    val time: String,
    val windGusts10m: Double,
    val windSpeed10m: Double
)