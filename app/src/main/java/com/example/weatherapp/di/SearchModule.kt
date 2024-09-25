package com.example.weatherapp.di

import com.example.weatherapp.data.mapper.WeatherMapper
import com.example.weatherapp.data.mapper.WeatherMapperImpl
import com.example.weatherapp.data.repository.SearchRepositoryImpl
import com.example.weatherapp.domain.SearchRepository
import org.koin.dsl.module

val searchModule = module {
    single<SearchRepository> { SearchRepositoryImpl(get(), get()) }

    factory<WeatherMapper> { WeatherMapperImpl() }
}