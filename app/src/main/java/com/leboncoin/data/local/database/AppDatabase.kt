package com.leboncoin.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leboncoin.Constants
import com.leboncoin.data.local.dao.AlbumDao
import com.leboncoin.data.model.Album

@Database(entities = [Album::class], version = Constants.Persistence.DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val albumDao: AlbumDao
}