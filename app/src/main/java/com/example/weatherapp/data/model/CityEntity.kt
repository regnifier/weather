package com.example.weatherapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class CityEntity(
    @PrimaryKey
    val id: Int = 0,
    val cityName: String = "",
    val countryCode: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
) : Parcelable