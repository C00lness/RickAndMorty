package com.example.rickandmortyworkmate.presentation

import android.app.Application
import com.example.rickandmortyworkmate.di.app.appModule
import com.example.rickandmortyworkmate.di.data.dataModule
import com.example.rickandmortyworkmate.di.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, domainModule, appModule)}
    }
}