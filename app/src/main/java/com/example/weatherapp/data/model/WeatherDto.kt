package com.example.weatherapp.data.model

import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "current")
    val current: CurrentDto? = null,
    @field:Json(name = "daily")
    val daily: DailyDto? = null,
    @field:Json(name = "daily_units")
    val dailyUnits: DailyUnitsDto? = null,
    @field:Json(name = "hourly")
    val hourly: HourlyDto? = null,
    @field:Json(name = "hourly_units")
    val hourlyUnits: HourlyUnitsDto? = null
)

data class HourlyUnitsDto(
    @field:Json(name = "temperature_2m")
    val temperature2m: String? = null
)

data class HourlyDto(
    @field:Json(name = "temperature_2m")
    val temperature2m: List<Double>? = null,
    @field:Json(name = "time")
    val time: List<String>? = null
)

data class DailyUnitsDto(
    @field:Json(name = "temperature_2m_max")
    val temperature2mMax: String? = null
)

data class DailyDto(
    @field:Json(name = "temperature_2m_max")
    val temperature2mMax: List<Double>? = null,
    @field:Json(name = "time")
    val time: List<String>? = null
)

data class CurrentDto(
    @field:Json(name = "pressure_msl")
    val pressureMsl: Double? = null,
    @field:Json(name = "relative_humidity_2m")
    val relativeHumidity2m: Int? = null,
    @field:Json(name = "temperature_2m")
    val temperature2m: Double? = null,
    @field:Json(name = "time")
    val time: String? = null,
    @field:Json(name = "wind_gusts_10m")
    val windGusts10m: Double? = null,
    @field:Json(name = "wind_speed_10m")
    val windSpeed10m: Double? = null
)