package com.mironenko.gifviewer.screens.giflist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mironenko.gifviewer.utils.PRELOAD_ARTICLES
import com.mironenko.gifviewer.R
import com.mironenko.gifviewer.databinding.LayoutGifCardBinding
import com.mironenko.gifviewer.model.Gif

interface AdapterActionListener {
    fun loadMoreGif(page: Int)
}

class GifAdapter(
    private val actionListener: AdapterActionListener
) : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

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

        if (position == gifList.size - PRELOAD_ARTICLES) {
            actionListener.loadMoreGif(gifList.size)
        }

        holder.itemView.setOnClickListener {
            val action =
                GifGridFragmentDirections.actionGifGridFragmentToGifDetailsFragment(
                    currentGifId = gifEntity.id
                )

            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = gifList.size

    class GifViewHolder(private val binding: LayoutGifCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gif: Gif) {
            Glide.with(binding.ivGif.context)
                .load(gif.downSized_small)
                .placeholder(R.drawable.progress_animated)
                .error(R.drawable.ic_error)
                .into(binding.ivGif)

            binding.ivGif.contentDescription = gif.title

        }
    }
}