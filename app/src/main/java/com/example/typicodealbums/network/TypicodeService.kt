package com.example.typicodealbums.network

import com.example.typicodealbums.dataclass.Album
import io.reactivex.Single
import retrofit2.http.GET

interface TypicodeService {

    @GET("albums")
    fun getAlbums(): Single<List<Album>>
}