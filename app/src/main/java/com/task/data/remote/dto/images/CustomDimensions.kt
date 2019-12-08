package com.task.data.remote.dto.images


import com.google.gson.annotations.SerializedName

data class CustomDimensions(
    @SerializedName("10")
    val dimension_10: String?,
    @SerializedName("126")
    val dimension_126: String?,
    @SerializedName("47")
    val dimension_47: String?
)