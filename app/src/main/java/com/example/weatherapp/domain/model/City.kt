package com.example.weatherapp.domain.model

data class City(
    val cityName: String,
    val countryCode: String,
    val latitude: Double,
    val longitude: Double,
    val id: Int
)