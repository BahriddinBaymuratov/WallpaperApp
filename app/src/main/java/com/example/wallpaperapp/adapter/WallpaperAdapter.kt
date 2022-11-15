package com.example.wallpaperapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp.database.Image
import com.example.wallpaperapp.databinding.ImageLayoutBinding

class WallpaperAdapter : RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>() {

    lateinit var onItemClicked: (Image) -> Unit
    var imageList: MutableList<Image> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        return WallpaperViewHolder(
            ImageLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        holder.find(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size

    inner class WallpaperViewHolder(private val binding: ImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun find(image: Image) {
            binding.imageView.setImageResource(image.image)
            itemView.setOnClickListener {
                onItemClicked.invoke(image)
            }
        }

    }

}