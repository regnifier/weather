package com.example.weatherapp.di

import com.example.weatherapp.data.mapper.WeatherMapper
import com.example.weatherapp.data.mapper.WeatherMapperImpl
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.WeatherRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val searchModule = module {
    single<WeatherRepository> {
        WeatherRepositoryImpl(
            ioDispatcher = Dispatchers.IO,
            weatherApi = get(),
            mapper = get(),
            cityDao = get()
        )
    }

    factory<WeatherMapper> { WeatherMapperImpl() }
}