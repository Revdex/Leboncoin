package com.leboncoin.ui.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leboncoin.data.model.Album
import com.leboncoin.data.repository.AlbumRepository
import com.leboncoin.di.CoroutineMainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val repository: AlbumRepository,
    @CoroutineMainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<AlbumsUiState>(AlbumsUiState.NAN)
    val uiState = _uiState.asStateFlow()

    init {
        loadAlbums()
    }

    fun loadAlbums() {
        viewModelScope.launch(dispatcher) {
            repository
                .getAlbums()
                .onStart { _uiState.value = AlbumsUiState.Loading }
                .catch { exception -> AlbumsUiState.Error(exception) }
                .collect { albums ->
                    if (albums.isEmpty()) {
                        _uiState.value = AlbumsUiState.Empty
                    } else {
                        _uiState.value = AlbumsUiState.Success(albums)
                    }
                }
        }
    }


}

sealed class AlbumsUiState {
    data class Success(val albums: List<Album>) : AlbumsUiState()
    data class Error(val exception: Throwable) : AlbumsUiState()
    object Loading : AlbumsUiState()
    object Empty : AlbumsUiState()
    object NAN : AlbumsUiState()

}