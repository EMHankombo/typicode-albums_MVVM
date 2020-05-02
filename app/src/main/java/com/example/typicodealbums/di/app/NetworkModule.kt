package com.example.typicodealbums.di.app

import com.example.typicodealbums.common.Constants
import com.example.typicodealbums.network.TypicodeService
import com.example.typicodealbums.repository.RemoteDataSource
import com.example.typicodealbums.repository.RemoteDataSourceImp
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideTypicodeService(retrofit: Retrofit): TypicodeService = retrofit.create(TypicodeService::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(typicodeService: TypicodeService) : RemoteDataSource {
        return RemoteDataSourceImp(typicodeService)
    }

}