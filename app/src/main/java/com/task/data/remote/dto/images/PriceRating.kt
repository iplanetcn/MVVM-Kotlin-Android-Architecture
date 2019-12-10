package com.task.data.remote.dto.images

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PriceRating(
    var noRatingReason: String?,
    var rating: String?,
    var ratingLabel: String?
): Parcelable