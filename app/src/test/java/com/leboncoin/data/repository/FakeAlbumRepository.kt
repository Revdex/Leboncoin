package com.leboncoin.data.repository

import com.leboncoin.data.model.Album
import kotlinx.coroutines.flow.flow

class FakeAlbumRepository : AlbumRepository {

    private val albums = mutableListOf<Album>()

    private var shouldThrowException = false

    fun setShouldThrowException(value: Boolean) {
        shouldThrowException = value
    }

    override suspend fun insertAlbums(newAlbums: List<Album>) {
        albums.addAll(newAlbums)
    }

    override suspend fun deleteAlbums() {
        albums.clear()
    }

    override fun getAlbums() = flow {
        if (shouldThrowException) {
            throw Exception("Unknown Exception")
        } else {
            emit(albums)
        }
    }

}