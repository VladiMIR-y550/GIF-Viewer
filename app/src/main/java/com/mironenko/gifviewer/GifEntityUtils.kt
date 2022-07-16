package com.mironenko.gifviewer

import com.mironenko.gifviewer.model.Gif
import com.mironenko.gifviewer.model.GifEntity
import com.mironenko.gifviewer.model.GifListRepository

fun GifListRepository.toGif(gifEntity: GifEntity) =
    Gif(
        id = gifEntity.id,
        title = gifEntity.title,
        source = gifEntity.source,
        images = gifEntity.images,
        bitly_gif_url = gifEntity.bitly_gif_url,
        bitly_url = gifEntity.bitly_url,
        content_url = gifEntity.content_url,
        embed_url = gifEntity.embed_url,
        import_datetime = gifEntity.import_datetime,
        rating = gifEntity.rating,
        author_name = gifEntity.username,
        preview = gifEntity.images.preview_gif.url,
        url = gifEntity.url,
        downSized = gifEntity.images.downsized_medium.url
    )