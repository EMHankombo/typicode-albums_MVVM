package com.example.typicodealbums.di.albums

import com.example.typicodealbums.ui.AlbumsActivity
import com.example.typicodealbums.di.app.AppComponent
import dagger.Component

@ActivityScope
@Component(modules = [AlbumsActivityModule::class],dependencies = [AppComponent::class])
interface AlbumsActivityComponent {
    fun inject(albumsActivity: AlbumsActivity)

}