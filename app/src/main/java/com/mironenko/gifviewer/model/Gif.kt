package com.mironenko.gifviewer.model

data class Gif(
    val id: String,
    val title: String,
    val source: String,
    val import_datetime: String,
    val rating: String,
    val author_name: String?,
    val preview: String,
    val url: String,
    val downSized_small: String,
    val downSized_large: String
)