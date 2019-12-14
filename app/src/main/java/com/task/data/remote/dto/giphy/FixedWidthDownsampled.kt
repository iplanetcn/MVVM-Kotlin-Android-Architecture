package com.task.data.remote.dto.giphy


import com.google.gson.annotations.SerializedName

data class FixedWidthDownsampled(
    var height: String?,
    var size: String?,
    var url: String?,
    var webp: String?,
    @SerializedName("webp_size")
    var webpSize: String?,
    var width: String?
)