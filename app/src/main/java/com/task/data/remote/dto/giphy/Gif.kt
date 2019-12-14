package com.task.data.remote.dto.giphy


import com.google.gson.annotations.SerializedName

data class Gif(
        var analytics: Analytics?,
        @SerializedName("bitly_gif_url")
        var bitlyGifUrl: String = "",
        @SerializedName("bitly_url")
        var bitlyUrl: String?,
        @SerializedName("content_url")
        var contentUrl: String?,
        @SerializedName("embed_url")
        var embedUrl: String?,
        var id: String?,
        var images: Images?,
        @SerializedName("import_datetime")
        var importDatetime: String?,
        @SerializedName("is_sticker")
        var isSticker: Int?,
        var rating: String?,
        var slug: String?,
        var source: String?,
        @SerializedName("source_post_url")
        var sourcePostUrl: String?,
        @SerializedName("source_tld")
        var sourceTld: String?,
        var title: String?,
        @SerializedName("trending_datetime")
        var trendingDatetime: String?,
        var type: String?,
        var url: String?,
        var user: User?,
        var username: String?
)