package com.example.weatherapp.data.model

import com.squareup.moshi.Json

data class CityDto(
    @field:Json(name = "results")
    val results: List<CityDataDto>? = null
)

data class CityDataDto(
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "country")
    val country: String? = null,
    @field:Json(name = "latitude")
    val latitude: Double? = null,
    @field:Json(name = "longitude")
    val longitude: Double? = null,
    @field:Json(name = "id")
    val id: Int? = null
)