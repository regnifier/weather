package com.example.weatherapp.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("current")
    val current: CurrentDto? = null,
    @SerialName("daily")
    val daily: DailyDto? = null,
    @SerialName("daily_units")
    val dailyUnits: DailyUnitsDto? = null,
    @SerialName("hourly")
    val hourly: HourlyDto? = null,
    @SerialName("hourly_units")
    val hourlyUnits: HourlyUnitsDto? = null
)

@Serializable
data class HourlyUnitsDto(
    @SerialName("temperature_2m")
    val temperature2m: String? = null
)

@Serializable
data class HourlyDto(
    @SerialName("temperature_2m")
    val temperature2m: List<Double>? = null,
    @SerialName("time")
    val time: List<String>? = null
)

@Serializable
data class DailyUnitsDto(
    @SerialName("temperature_2m_max")
    val temperature2mMax: String? = null
)

@Serializable
data class DailyDto(
    @SerialName("temperature_2m_max")
    val temperature2mMax: List<Double>? = null,
    @SerialName("time")
    val time: List<String>? = null
)

@Serializable
data class CurrentDto(
    @SerialName("pressure_msl")
    val pressureMsl: Double? = null,
    @SerialName("relative_humidity_2m")
    val relativeHumidity2m: Int? = null,
    @SerialName("temperature_2m")
    val temperature2m: Double? = null,
    @SerialName("time")
    val time: String? = null,
    @SerialName("wind_gusts_10m")
    val windGusts10m: Double? = null,
    @SerialName("wind_speed_10m")
    val windSpeed10m: Double? = null
)