package com.example.weatherapp.data.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.model.CityEntity
import com.example.weatherapp.domain.model.City

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: CityEntity)

    @Query("SELECT * FROM cityentity")
    fun getAll(): List<CityEntity>
}