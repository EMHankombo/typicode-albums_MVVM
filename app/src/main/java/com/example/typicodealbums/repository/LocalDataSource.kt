package com.example.typicodealbums.repository

import com.example.typicodealbums.dataclass.Album
import io.reactivex.Single

interface LocalDataSource {

    fun getAlbums(): Single<List<Album>>

    fun addAlbums(albums:List<Album>)
}