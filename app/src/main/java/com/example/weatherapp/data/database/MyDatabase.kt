package com.example.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.data.api.CityDao
import com.example.weatherapp.data.model.CityEntity

const val MY_DATA_BASE = "my-database"

@Database(entities = [CityEntity::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getCityDao(): CityDao
}