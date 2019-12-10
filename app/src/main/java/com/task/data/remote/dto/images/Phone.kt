package com.task.data.remote.dto.images

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Phone(
    var number: String?,
    var type: String?,
    var uri: String?
): Parcelable