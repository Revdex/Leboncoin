package com.leboncoin.data.remote

import com.leboncoin.data.remote.service.ServiceEndpoint
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumRemoteDataSource @Inject constructor(private val api: ServiceEndpoint) {

    suspend fun getAlbums() = api.getAlbums()


}