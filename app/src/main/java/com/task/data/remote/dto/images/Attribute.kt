package com.task.data.remote.dto.images

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Attribute(
    var label: String?,
    var tag: String?,
    var varue: String?
): Parcelable