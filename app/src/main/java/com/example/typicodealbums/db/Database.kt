package com.example.typicodealbums.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.typicodealbums.common.Constants
import com.example.typicodealbums.dataclass.Album

@Database(entities = [Album::class],version = Constants.DATABASE_VERSION,exportSchema = false)
abstract class Database: RoomDatabase(){
    abstract fun albumsDao():AlbumsDao
}