package com.example.typicodealbums

import android.app.Application
import com.example.typicodealbums.di.app.*

class MyApp : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().networkModule(NetworkModule())
            .databaseModule(DatabaseModule(this)).build()

    }

    fun component() = appComponent
}