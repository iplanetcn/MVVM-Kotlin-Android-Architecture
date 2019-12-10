package com.task.data.remote.dto.images

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rating(
    var adRealityRate: Int?,
    var count: Int?,
    var link: String?,
    var recommendationRate: Int?,
    var score: Double?,
    var scoreLocalized: String?
): Parcelable