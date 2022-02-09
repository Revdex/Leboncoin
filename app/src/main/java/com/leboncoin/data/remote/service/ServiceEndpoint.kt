package com.leboncoin.data.remote.service

import com.leboncoin.data.model.Album
import retrofit2.http.GET

interface ServiceEndpoint {

    @GET(ALBUMS)
    suspend fun getAlbums(): List<Album>

    companion object {
        /*WS*/
        const val ALBUMS = "technical-test.json"

        /*Params*/
        const val PAGE = "page"
    }
}