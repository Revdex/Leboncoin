package com.leboncoin.data.local.dao

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.leboncoin.data.local.database.AppDatabase
import com.leboncoin.data.model.Album
import com.leboncoin.di.PersistenceModule
import com.leboncoin.di.RepositoryModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlin.random.Random

@ExperimentalCoroutinesApi
@SmallTest
@UninstallModules(PersistenceModule::class, RepositoryModule::class)
@HiltAndroidTest
class AlbumDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database: AppDatabase
    lateinit var albumDao: AlbumDao

    @Before
    fun setUp() {
        hiltRule.inject()
        albumDao = database.albumDao
    }

    @Test
    fun shouldInsertAlbumInDB() = runTest {
        val album = Album(
            id = Random.nextInt(100),
            albumId = 1,
            title = "reprehenderit est deserunt velit ipsam",
            url = "https://via.placeholder.com/600/771796",
            thumbnailUrl = "https://via.placeholder.com/150/771796"
        )
        albumDao.insert(album)
        val albums = albumDao.findAll()
        assertThat(albums).contains(album)
    }

    @Test
    fun shouldDeleteAlbumFormDB() = runTest {
        val album = Album(
            id = Random.nextInt(100),
            albumId = 1,
            title = "reprehenderit est deserunt velit ipsam",
            url = "https://via.placeholder.com/600/771796",
            thumbnailUrl = "https://via.placeholder.com/150/771796"
        )
        albumDao.insert(album)
        albumDao.delete(album)
        val albums = albumDao.findAll()
        assertThat(albums).doesNotContain(album)
    }


    @After
    fun tearDown() {
        database.close()
    }
}