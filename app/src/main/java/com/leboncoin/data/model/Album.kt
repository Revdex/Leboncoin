package com.leboncoin.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "album")
data class Album(
    @JsonProperty("id")
    @PrimaryKey val id: Int,
    @JsonProperty("albumId")
    val albumId: Int,
    @JsonProperty("thumbnailUrl")
    val thumbnailUrl: String,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("url")
    val url: String
)