package com.task.data.remote.dto.productImages

data class ProductImages(
    val attributes: List<Attribute?>?,
    val contact: Contact?,
    val created: Int?,
    val customDimensions: CustomDimensions?,
    val features: List<String?>?,
    val financeBudget: FinanceBudget?,
    val htmlDescription: String?,
    val id: Int?,
    val images: List<Image?>?,
    val isConditionNew: Boolean?,
    val isDamageCase: Boolean?,
    val isFinancingFeature: Boolean?,
    val isNew: Boolean?,
    val links: List<Link?>?,
    val makeId: Int?,
    val makeKey: String?,
    val mediaGallery: MediaGallery?,
    val modelId: Int?,
    val modelKey: String?,
    val modified: Int?,
    val price: Price?,
    val priceRating: PriceRating?,
    val readyToDrive: Boolean?,
    val renewed: Int?,
    val segment: String?,
    val sellerId: Int?,
    val title: String?,
    val url: String?,
    val vc: String?,
    val version: Int?
)

data class Attribute(
    val label: String?,
    val tag: String?,
    val value: String?
)

data class Contact(
    val address1: String?,
    val address2: String?,
    val canSendCcMail: Boolean?,
    val country: String?,
    val enumType: String?,
    val homepageUrl: String?,
    val languages: String?,
    val latLong: LatLong?,
    val name: String?,
    val phones: List<Phone?>?,
    val rating: Rating?,
    val type: String?,
    val withMobileSince: String?
)

data class LatLong(
    val lat: Double?,
    val lon: Double?
)

data class Phone(
    val number: String?,
    val type: String?,
    val uri: String?
)

data class Rating(
    val adRealityRate: Int?,
    val count: Int?,
    val link: String?,
    val recommendationRate: Int?,
    val score: Double?,
    val scoreLocalized: String?
)

data class CustomDimensions(
    val `10`: String?,
    val `126`: String?,
    val `47`: String?
)

data class FinanceBudget(
    val budgetStatus: String?,
    val downPayment: Int?,
    val loanDuration: Int?,
    val localized: Localized?,
    val netIncome: Int?
)

data class Localized(
    val downPayment: String?,
    val netIncome: String?
)

data class Image(
    val `set`: String?,
    val uri: String?
)

data class Link(
    val href: String?,
    val rel: String?
)

data class MediaGallery(
    val additionalAds: Boolean?,
    val slideShow: Boolean?
)

data class Price(
    val grs: Grs?,
    val type: String?
)

data class Grs(
    val amount: Int?,
    val currency: String?,
    val localized: String?
)

data class PriceRating(
    val noRatingReason: String?,
    val rating: String?,
    val ratingLabel: String?
)