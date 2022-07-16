package com.mironenko.gifviewer.screens.giflist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mironenko.gifviewer.R
import com.mironenko.gifviewer.databinding.LayoutGifCardBinding
import com.mironenko.gifviewer.model.Gif

class GifAdapter : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

    var gifList: List<Gif> = emptyList()
    set(newValue) {
        val diffCallback = GifGridDiffCallback(field, newValue)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        field = newValue
        diffResult.dispatchUpdatesTo(this)
    }

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
        fun bind(gif: Gif) {
            Log.d("TAG", "URL = ${gif.url}")
            Glide.with(binding.ivGif.context)
                .load(gif.downSized)
                .placeholder(R.drawable.ic_gif)
                .error(R.drawable.ic_error)
                .into(binding.ivGif)
        }
    }
}