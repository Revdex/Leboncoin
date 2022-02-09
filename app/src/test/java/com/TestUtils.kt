package com

import com.leboncoin.data.model.Album
import kotlin.random.Random

object TestUtils {

    fun generateAlbum() = Album(
        id = Random.nextInt(100),
        albumId = 1,
        title = "reprehenderit est deserunt velit ipsam",
        url = "https://via.placeholder.com/600/771796",
        thumbnailUrl = "https://via.placeholder.com/150/771796"
    )

    fun generateAlbumList() = arrayListOf(
        Album(
            id = 1,
            albumId = 1,
            title = "reprehenderit est deserunt velit ipsam",
            url = "https://via.placeholder.com/600/771796",
            thumbnailUrl = "https://via.placeholder.com/150/771796"
        ),
        Album(
            id = 2,
            albumId = 1,
            title = "repudiandae iusto deleniti rerum",
            url = "https://via.placeholder.com/600/66b7d2",
            thumbnailUrl = "https://via.placeholder.com/150/66b7d2"
        ),
        Album(
            id = 3,
            albumId = 1,
            title = "harum dicta similique quis dolore earum ex qui",
            url = "https://via.placeholder.com/600/f9cee5",
            thumbnailUrl = "https://via.placeholder.com/150/f9cee5"
        ),
        Album(
            id = 4,
            albumId = 2,
            title = "iusto sunt nobis quasi veritatis quas expedita voluptatum deserunt",
            url = "https://via.placeholder.com/600/fdf73e",
            thumbnailUrl = "https://via.placeholder.com/150/fdf73e"
        ),
        Album(
            id = 5,
            albumId = 1,
            title = "reprehenderit est deserunt velit ipsam",
            url = "https://via.placeholder.com/600/771796",
            thumbnailUrl = "https://via.placeholder.com/150/771796"
        ),
        Album(
            id = 6,
            albumId = 2,
            title = "assumenda voluptatem laboriosam enim consequatur veniam placeat reiciendis error",
            url = "https://via.placeholder.com/600/8985dc",
            thumbnailUrl = "https://via.placeholder.com/150/8985dc"
        ),
    )
}