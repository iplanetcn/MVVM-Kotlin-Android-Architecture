package com.task.data.remote.dto.images

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Localized(
    var downPayment: String?,
    var netIncome: String?
): Parcelable