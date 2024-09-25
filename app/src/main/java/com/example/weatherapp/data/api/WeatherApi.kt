package com.example.weatherapp.data.api

import com.example.weatherapp.data.model.CityDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/search")
    suspend fun searchCity(
        @Query("name") city: String
    ): CityDto
}