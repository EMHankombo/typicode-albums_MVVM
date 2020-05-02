package com.example.typicodealbums.repository

import com.example.typicodealbums.dataclass.Album
import com.example.typicodealbums.network.TypicodeService
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val typicodeService: TypicodeService): RemoteDataSource {

    override fun getAlbums(): Single<List<Album>> {
        return typicodeService.getAlbums()
    }
}