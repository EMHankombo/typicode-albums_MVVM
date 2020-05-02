package com.example.typicodealbums.repository

import com.example.typicodealbums.dataclass.Album
import io.reactivex.Observable

interface AlbumsRepository {
    fun getAlbumsSortedAlbums(): Observable<List<Album>>

    fun getDataFromDb():Observable<List<Album>>
}