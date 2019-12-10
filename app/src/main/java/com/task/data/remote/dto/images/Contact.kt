package com.task.data.remote.dto.images

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
        var address1: String?,
        var address2: String?,
        var canSendCcMail: Boolean?,
        var country: String?,
        var enumType: String?,
        var homepageUrl: String?,
        var languages: String?,
        var latLong: LatLong?,
        var name: String?,
        var phones: List<Phone?>?,
        var rating: Rating?,
        var type: String?,
        var withMobileSince: String?
) : Parcelable