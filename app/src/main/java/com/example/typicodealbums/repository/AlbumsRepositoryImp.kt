package com.example.typicodealbums.repository

import com.example.typicodealbums.dataclass.Album
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AlbumsRepositoryImp (private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource):AlbumsRepository {

    override fun getAlbumsSortedAlbums(): Observable<List<Album>> {
      return remoteDataSource.getAlbums()
          .doOnSuccess(this::addDataToDb)
          .toObservable()
          .onErrorResumeNext(getDataFromDb())
          .flatMap { mapper -> Observable.fromIterable(mapper) }
          .toSortedList { album, album2 -> album.title.compareTo(album2.title) }
          .toObservable()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())


    }

     override fun getDataFromDb(): Observable<List<Album>>{

        return localDataSource.getAlbums().toObservable()
    }


    private fun addDataToDb(albums: List<Album>){
     localDataSource.addAlbums(albums)
    }
}