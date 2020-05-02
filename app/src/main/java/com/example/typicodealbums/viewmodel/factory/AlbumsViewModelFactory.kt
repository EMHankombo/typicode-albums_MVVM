package com.example.typicodealbums.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.typicodealbums.repository.AlbumsRepository
import com.example.typicodealbums.viewmodel.AlbumsViewModel

class AlbumsViewModelFactory(private val albumsRepository: AlbumsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumsViewModel(albumsRepository) as T
    }
}