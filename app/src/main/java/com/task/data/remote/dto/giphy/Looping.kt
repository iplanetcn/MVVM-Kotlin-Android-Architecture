package com.task.data.remote.dto.giphy


import com.google.gson.annotations.SerializedName

data class Looping(
    var mp4: String?,
    @SerializedName("mp4_size")
    var mp4Size: String?
)