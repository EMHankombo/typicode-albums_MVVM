package com.example.typicodealbums.di.app

import com.example.typicodealbums.MyApp
import com.example.typicodealbums.repository.LocalDataSource
import com.example.typicodealbums.repository.RemoteDataSource
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,DatabaseModule::class])
interface AppComponent {
    fun inject(myApp: MyApp)

    fun remoteDataSource():RemoteDataSource

    fun localDataSource(): LocalDataSource


}