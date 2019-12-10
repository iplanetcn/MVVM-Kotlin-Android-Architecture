package com.task.data.remote.dto.images

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
        @SerializedName("set") var imagesSet: String?,
        var uri: String?
): Parcelable