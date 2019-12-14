package com.task.data.remote.dto.giphy


import com.google.gson.annotations.SerializedName

data class FixedHeight(
    var height: String?,
    var mp4: String?,
    @SerializedName("mp4_size")
    var mp4Size: String?,
    var size: String?,
    var url: String?,
    var webp: String?,
    @SerializedName("webp_size")
    var webpSize: String?,
    var width: String?
)