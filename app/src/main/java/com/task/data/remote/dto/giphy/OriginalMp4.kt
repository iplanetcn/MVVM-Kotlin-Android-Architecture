package com.task.data.remote.dto.giphy


import com.google.gson.annotations.SerializedName

data class OriginalMp4(
    var height: String?,
    var mp4: String?,
    @SerializedName("mp4_size")
    var mp4Size: String?,
    var width: String?
)