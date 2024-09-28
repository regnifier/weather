package com.example.weatherapp.di

import androidx.room.Room
import com.example.weatherapp.data.database.MY_DATA_BASE
import com.example.weatherapp.data.database.MyDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { Room.databaseBuilder(get(),MyDatabase::class.java, MY_DATA_BASE).build() }
    single { get<MyDatabase>().getCityDao() }
}