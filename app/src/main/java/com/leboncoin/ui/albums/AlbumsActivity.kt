package com.leboncoin.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.leboncoin.R
import com.leboncoin.data.model.Album
import com.leboncoin.data.repository.DefaultAlbumRepository
import com.leboncoin.databinding.ActivityAlbumsBinding
import com.leboncoin.ui.albums.AlbumAdapter
import com.leboncoin.ui.albums.AlbumsUiState
import com.leboncoin.ui.albums.AlbumsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AlbumsActivity : AppCompatActivity(), AlbumAdapter.OnItemClickListener {

    private val viewModel by viewModels<AlbumsViewModel>()
    private lateinit var binding: ActivityAlbumsBinding
    private lateinit var albumAdapter: AlbumAdapter

    @Inject
    lateinit var repository: DefaultAlbumRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupUI()
        setup()
    }

    private fun setupUI() {
        albumAdapter = AlbumAdapter(this)
        binding.apply {
            albums.apply {
                adapter = albumAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }
        }
    }

    private fun loadData() {
        viewModel.loadAlbums()
    }

    private fun setup() {
        lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest { uiState ->
                    when (uiState) {
                        is AlbumsUiState.Success -> {
                            binding.loader.isVisible = false
                            binding.albums.isVisible = true
                            albumAdapter.submitList(uiState.albums)
                        }
                        is AlbumsUiState.Error -> {
                            binding.loader.isVisible = false
                            binding.albums.isVisible = false
                            Snackbar.make(binding.root, R.string.msg_error, Snackbar.LENGTH_INDEFINITE)
                                .setAction(R.string.retry) {
                                    loadData()
                                }.show()
                        }
                        is AlbumsUiState.Loading -> {
                            binding.loader.isVisible = true
                            binding.albums.isVisible = false
                        }
                        is AlbumsUiState.Empty -> {
                            binding.loader.isVisible = false
                            binding.albums.isVisible = false
                            Snackbar.make(binding.root, R.string.msg_no_data, Snackbar.LENGTH_INDEFINITE)
                                .setAction(R.string.retry) {
                                    loadData()
                                }.show()
                        }

                    }

                }
        }
    }

    override fun onItemClick(album: Album) {
    }
}
