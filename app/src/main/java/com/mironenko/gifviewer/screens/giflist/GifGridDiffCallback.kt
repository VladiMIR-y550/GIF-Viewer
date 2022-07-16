package com.mironenko.gifviewer.screens.giflist

import androidx.recyclerview.widget.DiffUtil
import com.mironenko.gifviewer.model.Gif

class GifGridDiffCallback(
    private val oldList: List<Gif>,
    private val newList: List<Gif>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldArticle = oldList[oldItemPosition]
        val newArticle = newList[newItemPosition]
        return oldArticle.id == newArticle.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldArticle = oldList[oldItemPosition]
        val newArticle = newList[newItemPosition]
        return oldArticle == newArticle
    }
}