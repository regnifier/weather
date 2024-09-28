package com.example.weatherapp.data.api

import com.example.weatherapp.data.model.CityDto
import com.example.weatherapp.data.model.CurrentDto
import com.example.weatherapp.data.model.WeatherDto
import com.example.weatherapp.utils.PRESSURE_MSL
import com.example.weatherapp.utils.RAIN
import com.example.weatherapp.utils.RELATIVE_HUMIDITY_2M
import com.example.weatherapp.utils.TEMPERATURE_2M
import com.example.weatherapp.utils.TEMPERATURE_2M_MAX
import com.example.weatherapp.utils.WEATHER_CODE
import com.example.weatherapp.utils.WIND_DIRECTION_10M
import com.example.weatherapp.utils.WIND_GUSTS_10M
import com.example.weatherapp.utils.WIND_SPEED_10M
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface WeatherApi {
    @GET("v1/search")
    suspend fun searchCity(
        @Query("name") city: String
    ): CityDto

    @GET
    suspend fun checkWeather(
        @Url url: String = "https://api.open-meteo.com/v1/forecast",
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = listOf(
            TEMPERATURE_2M,
            RELATIVE_HUMIDITY_2M,
            RAIN,
            WEATHER_CODE,
            PRESSURE_MSL,
            WIND_SPEED_10M,
            WIND_DIRECTION_10M,
            WIND_GUSTS_10M
        ).joinToString(separator = ","),
        @Query("hourly") hourly: String = TEMPERATURE_2M,
        @Query("daily") daily: String = TEMPERATURE_2M_MAX
    ): WeatherDto
}