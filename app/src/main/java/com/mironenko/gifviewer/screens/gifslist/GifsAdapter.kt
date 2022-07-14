package com.mironenko.gifviewer.screens.gifslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mironenko.gifviewer.R
import com.mironenko.gifviewer.databinding.LayoutGifCardBinding
import com.mironenko.gifviewer.repository.GifEntity

class GifsAdapter : RecyclerView.Adapter<GifsAdapter.GifViewHolder>() {

    private val gifList: List<GifEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutGifCardBinding.inflate(inflater, parent, false)
        return GifViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gifEntity = gifList[position]
        holder.bind(gifEntity)
    }

    override fun getItemCount(): Int = gifList.size

    class GifViewHolder(private val binding: LayoutGifCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(gifEntity: GifEntity) {
            Glide.with(binding.ivGif.context)
                .load(gifEntity.url)
                .placeholder(R.drawable.ic_gif)
                .error(R.drawable.ic_error)
                .into(binding.ivGif)
        }
    }
}