package com.example.weatherapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CityDto(
    @SerialName("results")
    val results: List<CityDataDto>? = null
)

@Serializable
data class CityDataDto(
    @SerialName("name")
    val name: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("latitude")
    val latitude: Double? = null,
    @SerialName("longitude")
    val longitude: Double? = null,
    @SerialName("id")
    val id: Int? = null
)