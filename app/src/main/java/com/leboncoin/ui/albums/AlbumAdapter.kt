package com.leboncoin.ui.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.leboncoin.data.model.Album
import com.leboncoin.databinding.ItemAlbumBinding

class AlbumAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Album, AlbumAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class ViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position))
                    }
                }

            }
        }

        fun bind(album: Album) {
            binding.apply {
                thumbnail.load(album.thumbnailUrl)
                title.text = album.title

            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(album: Album)
    }

    class DiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Album, newItem: Album) =
            oldItem == newItem
    }
}