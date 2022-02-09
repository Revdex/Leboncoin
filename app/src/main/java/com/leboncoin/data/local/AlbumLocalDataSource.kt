package com.leboncoin.data.local

import com.leboncoin.data.local.dao.AlbumDao
import com.leboncoin.data.model.Album
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumLocalDataSource @Inject constructor(private val albumDao: AlbumDao) {

    suspend fun insert(albums: List<Album>) {
        albumDao.insert(albums)
    }

    suspend fun insert(album: Album) {
        albumDao.insert(album)
    }

    suspend fun update(album: Album) {
        albumDao.update(album)
    }

    suspend fun delete(album: Album) {
        albumDao.delete(album)
    }

    suspend fun deleteAll() {
        albumDao.deleteAll()
    }

    suspend fun getAlbums(): List<Album> {
        return albumDao.findAll()
    }
}