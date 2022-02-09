package com.leboncoin.data.local.dao

import androidx.room.*
import com.leboncoin.data.model.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(albums: List<Album>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(album: Album)

    @Update
    suspend fun update(album: Album)

    @Delete
    suspend fun delete(album: Album)

    @Query("DELETE FROM album")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM album")
    suspend fun findAll(): List<Album>

}