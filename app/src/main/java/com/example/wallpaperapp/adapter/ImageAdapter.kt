package com.example.wallpaperapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp.database.Image
import com.example.wallpaperapp.databinding.ImageLayoutBinding

class ImageAdapter:ListAdapter<Image, ImageAdapter.ImageViewHolder>(DiffCallback()) {
    private class DiffCallback: DiffUtil.ItemCallback<Image>(){

        lateinit var onItemClick: (Image) -> Unit
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.find(getItem(position))
    }

    inner class ImageViewHolder(private val binding: ImageLayoutBinding):
            RecyclerView.ViewHolder(binding.root){
                fun find(image: Image){

                }
            }
}