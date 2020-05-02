package com.example.typicodealbums.repository

import com.example.typicodealbums.dataclass.Album
import io.reactivex.Single

interface RemoteDataSource {
    fun getAlbums():Single<List<Album>>
}