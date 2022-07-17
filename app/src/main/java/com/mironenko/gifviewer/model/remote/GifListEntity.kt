package com.mironenko.gifviewer.model.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GifListEntity(
    @Expose
    @SerializedName("data") val `data`: List<GifEntity>,
    @Expose
    @SerializedName("meta") val meta: Meta,
    @Expose
    @SerializedName("pagination") val pagination: Pagination
)

data class GifEntity(
    @Expose
    @SerializedName("analytics") val analytics: Analytics,
    @Expose
    @SerializedName("analytics_response_payload") val analytics_response_payload: String,
    @Expose
    @SerializedName("bitly_gif_url") val bitly_gif_url: String,
    @Expose
    @SerializedName("bitly_url") val bitly_url: String,
    @Expose
    @SerializedName("content_url") val content_url: String,
    @Expose
    @SerializedName("embed_url") val embed_url: String,
    @Expose
    @SerializedName("id") val id: String,
    @Expose
    @SerializedName("images") val images: Images,
    @Expose
    @SerializedName("import_datetime") val import_datetime: String,
    @Expose
    @SerializedName("is_sticker") val is_sticker: Int,
    @Expose
    @SerializedName("rating") val rating: String,
    @Expose
    @SerializedName("slug") val slug: String,
    @Expose
    @SerializedName("source") val source: String,
    @Expose
    @SerializedName("source_post_url") val source_post_url: String,
    @Expose
    @SerializedName("source_tld") val source_tld: String,
    @Expose
    @SerializedName("title") val title: String,
    @Expose
    @SerializedName("trending_datetime") val trending_datetime: String,
    @Expose
    @SerializedName("type") val type: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("user") val user: User,
    @Expose
    @SerializedName("username") val username: String
)

data class Meta(
    @Expose
    @SerializedName("msg") val msg: String,
    @Expose
    @SerializedName("response_id") val response_id: String,
    @Expose
    @SerializedName("status") val status: Int
)

data class Pagination(
    @Expose
    @SerializedName("count") val count: Int,
    @Expose
    @SerializedName("offset") val offset: Int,
    @Expose
    @SerializedName("total_count") val total_count: Int
)

data class Analytics(
    @Expose
    @SerializedName("onclick") val onclick: Onclick,
    @Expose
    @SerializedName("onload") val onload: Onload,
    @Expose
    @SerializedName("onsent") val onsent: Onsent
)

data class Images(
    @Expose
    @SerializedName("480w_still") val `480w_still`: WStill,
    @Expose
    @SerializedName("downsized") val downsized: Downsized,
    @Expose
    @SerializedName("downsized_large") val downsized_large: DownsizedLarge,
    @Expose
    @SerializedName("downsized_medium") val downsized_medium: DownsizedMedium,
    @Expose
    @SerializedName("downsized_small") val downsized_small: DownsizedSmall,
    @Expose
    @SerializedName("downsized_still") val downsized_still: DownsizedStill,
    @Expose
    @SerializedName("fixed_height") val fixed_height: FixedHeight,
    @Expose
    @SerializedName("fixed_height_downsampled") val fixed_height_downsampled: FixedHeightDownsampled,
    @Expose
    @SerializedName("fixed_height_small") val fixed_height_small: FixedHeightSmall,
    @Expose
    @SerializedName("fixed_height_small_still") val fixed_height_small_still: FixedHeightSmallStill,
    @Expose
    @SerializedName("fixed_height_still") val fixed_height_still: FixedHeightStill,
    @Expose
    @SerializedName("fixed_width") val fixed_width: FixedWidth,
    @Expose
    @SerializedName("fixed_width_downsampled") val fixed_width_downsampled: FixedWidthDownsampled,
    @Expose
    @SerializedName("fixed_width_small") val fixed_width_small: FixedWidthSmall,
    @Expose
    @SerializedName("fixed_width_small_still") val fixed_width_small_still: FixedWidthSmallStill,
    @Expose
    @SerializedName("fixed_width_still") val fixed_width_still: FixedWidthStill,
    @Expose
    @SerializedName("hd") val hd: Hd,
    @Expose
    @SerializedName("looping") val looping: Looping,
    @Expose
    @SerializedName("original") val original: Original,
    @Expose
    @SerializedName("original_mp4") val original_mp4: OriginalMp4,
    @Expose
    @SerializedName("original_still") val original_still: OriginalStill,
    @Expose
    @SerializedName("preview") val preview: Preview,
    @Expose
    @SerializedName("preview_gif") val preview_gif: PreviewGif,
    @Expose
    @SerializedName("preview_webp") val preview_webp: PreviewWebp
)

data class User(
    @Expose
    @SerializedName("avatar_url") val avatar_url: String,
    @Expose
    @SerializedName("banner_image") val banner_image: String,
    @Expose
    @SerializedName("banner_url") val banner_url: String,
    @Expose
    @SerializedName("description") val description: String,
    @Expose
    @SerializedName("display_name") val display_name: String,
    @Expose
    @SerializedName("instagram_url") val instagram_url: String,
    @Expose
    @SerializedName("is_verified") val is_verified: Boolean,
    @Expose
    @SerializedName("profile_url") val profile_url: String,
    @Expose
    @SerializedName("username") val username: String,
    @Expose
    @SerializedName("website_url") val website_url: String
)

data class Onclick(
    @Expose
    @SerializedName("url") val url: String
)

data class Onload(
    @Expose
    @SerializedName("url") val url: String
)

data class Onsent(
    @Expose
    @SerializedName("url") val url: String
)

data class WStill(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("width") val width: String
)

data class Downsized(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("width") val width: String
)

data class DownsizedLarge(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("width") val width: String
)

data class DownsizedMedium(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("width") val width: String
)

data class DownsizedSmall(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("mp4") val mp4: String,
    @Expose
    @SerializedName("mp4_size") val mp4_size: String,
    @Expose
    @SerializedName("width") val width: String
)

data class DownsizedStill(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("width") val width: String
)

data class FixedHeight(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("mp4") val mp4: String,
    @Expose
    @SerializedName("mp4_size") val mp4_size: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("webp") val webp: String,
    @Expose
    @SerializedName("webp_size") val webp_size: String,
    @Expose
    @SerializedName("width") val width: String
)

data class FixedHeightDownsampled(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("webp") val webp: String,
    @Expose
    @SerializedName("webp_size") val webp_size: String,
    @Expose
    @SerializedName("width") val width: String
)

data class FixedHeightSmall(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("mp4") val mp4: String,
    @Expose
    @SerializedName("mp4_size") val mp4_size: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("webp") val webp: String,
    @Expose
    @SerializedName("webp_size") val webp_size: String,
    @Expose
    @SerializedName("width") val width: String
)

data class FixedHeightSmallStill(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("width") val width: String
)

data class FixedHeightStill(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("width") val width: String
)

data class FixedWidth(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("mp4") val mp4: String,
    @Expose
    @SerializedName("mp4_size") val mp4_size: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("webp") val webp: String,
    @Expose
    @SerializedName("webp_size") val webp_size: String,
    @Expose
    @SerializedName("width") val width: String
)

data class FixedWidthDownsampled(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("webp") val webp: String,
    @Expose
    @SerializedName("webp_size") val webp_size: String,
    @Expose
    @SerializedName("width") val width: String
)

data class FixedWidthSmall(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("mp4") val mp4: String,
    @Expose
    @SerializedName("mp4_size") val mp4_size: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("webp") val webp: String,
    @Expose
    @SerializedName("webp_size") val webp_size: String,
    @Expose
    @SerializedName("width") val width: String
)

data class FixedWidthSmallStill(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("width") val width: String
)

data class FixedWidthStill(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("width") val width: String
)

data class Hd(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("mp4") val mp4: String,
    @Expose
    @SerializedName("mp4_size") val mp4_size: String,
    @Expose
    @SerializedName("width") val width: String
)

data class Looping(
    @Expose
    @SerializedName("mp4") val mp4: String,
    @Expose
    @SerializedName("mp4_size") val mp4_size: String
)

data class Original(
    @Expose
    @SerializedName("frames") val frames: String,
    @Expose
    @SerializedName("hash") val hash: String,
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("mp4") val mp4: String,
    @Expose
    @SerializedName("mp4_size") val mp4_size: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("webp") val webp: String,
    @Expose
    @SerializedName("webp_size") val webp_size: String,
    @Expose
    @SerializedName("width") val width: String
)

data class OriginalMp4(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("mp4") val mp4: String,
    @Expose
    @SerializedName("mp4_size") val mp4_size: String,
    @Expose
    @SerializedName("width") val width: String
)

data class OriginalStill(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("width") val width: String
)

data class Preview(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("mp4") val mp4: String,
    @Expose
    @SerializedName("mp4_size") val mp4_size: String,
    @Expose
    @SerializedName("width") val width: String
)

data class PreviewGif(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("width") val width: String
)

data class PreviewWebp(
    @Expose
    @SerializedName("height") val height: String,
    @Expose
    @SerializedName("size") val size: String,
    @Expose
    @SerializedName("url") val url: String,
    @Expose
    @SerializedName("width") val width: String
)