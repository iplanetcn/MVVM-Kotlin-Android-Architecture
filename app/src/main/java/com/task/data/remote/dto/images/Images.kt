package com.task.data.remote.dto.images

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Images(
        var attributes: List<Attribute?>?,
        var contact: Contact?,
        var created: Int?,
        var customDimensions: CustomDimensions?,
        var features: List<String?>?,
        var financeBudget: FinanceBudget?,
        var htmlDescription: String?,
        var id: Int?,
        var images: List<Image?>?,
        var isConditionNew: Boolean?,
        var isDamageCase: Boolean?,
        var isFinancingFeature: Boolean?,
        var isNew: Boolean?,
        var links: List<Link?>?,
        var makeId: Int?,
        var makeKey: String?,
        var mediaGallery: MediaGallery?,
        var modelId: Int?,
        var modelKey: String?,
        var modified: Int?,
        var price: Price?,
        var priceRating: PriceRating?,
        var readyToDrive: Boolean?,
        var renewed: Int?,
        var segment: String?,
        var sellerId: Int?,
        var title: String?,
        var url: String?,
        var vc: String?,
        var version: Int?
) : Parcelable