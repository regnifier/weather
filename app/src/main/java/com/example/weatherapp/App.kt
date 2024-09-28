package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.databaseModule
import com.example.weatherapp.di.networkModule
import com.example.weatherapp.di.searchModule
import com.example.weatherapp.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                databaseModule,
                networkModule,
                searchModule,
                viewModelsModule
            )
        }
    }
}