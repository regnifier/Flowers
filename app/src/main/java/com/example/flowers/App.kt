package com.example.flowers

import android.app.Application
import com.example.flowers.di.networkModule
import com.example.flowers.di.repositoryModule
import com.example.flowers.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR )
            androidContext(this@App)
            modules(
                listOf(
                    viewModelsModule,
                    repositoryModule,
                    networkModule
                )
            )
        }
    }
}