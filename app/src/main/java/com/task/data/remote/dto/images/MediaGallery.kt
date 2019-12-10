package com.task.data.remote.dto.images

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaGallery(
    var additionalAds: Boolean?,
    var slideShow: Boolean?
): Parcelable