package com.example.typicodealbums.di.albums

import androidx.lifecycle.ViewModelProviders
import com.example.typicodealbums.ui.AlbumsActivity
import com.example.typicodealbums.viewmodel.AlbumsViewModel
import com.example.typicodealbums.viewmodel.factory.AlbumsViewModelFactory
import com.example.typicodealbums.repository.AlbumsRepository
import com.example.typicodealbums.repository.AlbumsRepositoryImp
import com.example.typicodealbums.repository.LocalDataSource
import com.example.typicodealbums.repository.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class AlbumsActivityModule(private val albumsActivity: AlbumsActivity) {

    @Provides
    @ActivityScope
    fun provideHomeViewModelFactory(albumsRepository: AlbumsRepository): AlbumsViewModelFactory {

        return AlbumsViewModelFactory(
            albumsRepository
        )
    }

    @Provides
    @ActivityScope
    fun provideHomeViewModel(albumsActivityViewModelFactory: AlbumsViewModelFactory) =
        ViewModelProviders.of(albumsActivity,albumsActivityViewModelFactory).get(AlbumsViewModel::class.java)

    @Provides
    @ActivityScope
    fun provideAlbumRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): AlbumsRepository
            = AlbumsRepositoryImp(remoteDataSource, localDataSource)
}