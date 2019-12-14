package com.task.data.remote.dto.giphy


import com.google.gson.annotations.SerializedName

data class Meta(
    var msg: String?,
    @SerializedName("response_id")
    var responseId: String?,
    var status: Int?
)