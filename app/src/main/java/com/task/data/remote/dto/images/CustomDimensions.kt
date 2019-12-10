package com.task.data.remote.dto.images


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CustomDimensions(
        @SerializedName("10")
        var dimension_10: String?,
        @SerializedName("126")
        var dimension_126: String?,
        @SerializedName("47")
        var dimension_47: String?
) : Parcelable