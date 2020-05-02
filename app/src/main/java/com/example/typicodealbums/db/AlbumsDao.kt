package com.example.typicodealbums.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.typicodealbums.dataclass.Album
import io.reactivex.Single

@Dao
interface AlbumsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllAlbums(albums:List<Album>)

    @Query("SELECT * FROM albums_table")
    fun getAllAlbums(): Single<List<Album>>
}