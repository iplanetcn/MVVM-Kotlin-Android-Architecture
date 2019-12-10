package com.task.data.remote.dto.images

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LatLong(
    var lat: Double?,
    var lon: Double?
): Parcelable