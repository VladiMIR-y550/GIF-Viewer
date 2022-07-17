package com.mironenko.gifviewer.utils

import com.mironenko.gifviewer.model.Gif
import com.mironenko.gifviewer.model.GifListRepository
import com.mironenko.gifviewer.model.remote.GifEntity

fun GifListRepository.toGif(gifEntity: GifEntity) =
    Gif(
        id = gifEntity.id,
        title = gifEntity.title,
        source = gifEntity.source,
        import_datetime = gifEntity.import_datetime,
        rating = gifEntity.rating,
        author_name = gifEntity.username,
        preview = gifEntity.images.preview_gif.url,
        url = gifEntity.url,
        downSized_small = gifEntity.images.fixed_width_small.url,
        downSized_large = gifEntity.images.downsized_large.url
    )