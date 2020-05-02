package com.example.typicodealbums.di.app

import android.app.Application
import androidx.room.Room
import com.example.typicodealbums.common.Constants
import com.example.typicodealbums.db.Database
import com.example.typicodealbums.repository.LocalDataSource
import com.example.typicodealbums.repository.LocalDataSourceImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val application: Application){

    @Provides
    @Singleton
    fun provideAppDataBase(application: Application): Database{
        return Room.databaseBuilder(application,Database::class.java,Constants.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideLocalDataSource(database: Database): LocalDataSource {
        return LocalDataSourceImp(database)
    }
}