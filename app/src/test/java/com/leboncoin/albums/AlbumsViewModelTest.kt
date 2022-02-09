package com.leboncoin.albums

import com.TestUtils
import com.google.common.truth.Truth.assertThat
import com.leboncoin.data.repository.FakeAlbumRepository
import com.leboncoin.ui.albums.AlbumsUiState
import com.leboncoin.ui.albums.AlbumsViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
class AlbumsViewModelTest {

    private lateinit var viewModel: AlbumsViewModel
    private lateinit var fakeAlbumRepository: FakeAlbumRepository
    private lateinit var dispatcher: CoroutineDispatcher

    @Before
    fun setUp() {
        dispatcher = UnconfinedTestDispatcher()
        fakeAlbumRepository = FakeAlbumRepository()
        runTest {
            fakeAlbumRepository.insertAlbums(TestUtils.generateAlbumList())
        }
        viewModel = AlbumsViewModel(fakeAlbumRepository, dispatcher)

    }

    @After
    fun tearDown() {

    }

    @Test
    fun shouldReturnEmptyStateWhenListIsEmpty() = runTest {
        fakeAlbumRepository.deleteAlbums()
        viewModel = AlbumsViewModel(fakeAlbumRepository, dispatcher)
        val uiState = viewModel.uiState.first()
        assertThat(uiState).isEqualTo(AlbumsUiState.Empty)

    }

    @Test
    fun shouldReturnSuccessStateWhenListIsNotEmpty() = runTest {
        fakeAlbumRepository.insertAlbums(TestUtils.generateAlbumList())
        val uiState = viewModel.uiState.first()
        assertThat(uiState).isInstanceOf(AlbumsUiState.Success::class.java)
    }

    @Test
    fun shouldReturnErrorStateWhenThrowException() = runTest {
        fakeAlbumRepository.setShouldThrowException(true)
        val uiState = viewModel.uiState.first()
        assertThat(uiState).isInstanceOf(AlbumsUiState.Error::class.java)
    }
}