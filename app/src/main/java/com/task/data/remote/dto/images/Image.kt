package com.task.data.remote.dto.images

import com.google.gson.annotations.SerializedName


data class Image(

        @SerializedName("set") val imagesSet: String?,
        val uri: String?
)