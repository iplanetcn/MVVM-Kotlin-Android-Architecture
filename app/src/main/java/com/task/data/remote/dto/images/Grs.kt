package com.task.data.remote.dto.images

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Grs(
        var amount: Int?,
        var currency: String?,
        var localized: String?
) : Parcelable