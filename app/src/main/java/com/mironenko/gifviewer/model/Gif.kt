package com.mironenko.gifviewer.model

data class Gif(
    val id: String,
    val title: String,
    val source: String,
    val images: Images,
    val bitly_gif_url: String,
    val bitly_url: String,
    val content_url: String,
    val embed_url: String,
    val import_datetime: String,
    val rating: String,
    val author_name: String?,
    val author_image: String = "",
    val preview: String,
    val url: String,
    val downSized: String
    )