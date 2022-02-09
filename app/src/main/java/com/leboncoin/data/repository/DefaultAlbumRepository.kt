package com.leboncoin.data.repository

import com.leboncoin.data.local.AlbumLocalDataSource
import com.leboncoin.data.model.Album
import com.leboncoin.data.remote.AlbumRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultAlbumRepository @Inject constructor(
    private val localDataSource: AlbumLocalDataSource,
    private val remoteDataSource: AlbumRemoteDataSource
) : AlbumRepository {

    override suspend fun insertAlbums(albums: List<Album>) {
        localDataSource.insert(albums)
    }

    override suspend fun deleteAlbums() {
        localDataSource.deleteAll()
    }

    override fun getAlbums() = flow {
        val localAlbums = localDataSource.getAlbums()
        val remoteAlbums = remoteDataSource.getAlbums()
        if (localAlbums.isNotEmpty()) {
            deleteAlbums()
        }
        if (remoteAlbums.isNotEmpty()) {
            insertAlbums(remoteAlbums)
        }
        emit(localDataSource.getAlbums())
    }.flowOn(Dispatchers.IO)


}