package com.leboncoin.data.repository

import com.leboncoin.data.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {

    suspend fun insertAlbums(albums: List<Album>)

    suspend fun deleteAlbums()

    fun getAlbums(): Flow<List<Album>>
}