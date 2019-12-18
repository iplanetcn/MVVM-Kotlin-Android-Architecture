package com.task.data.remote.dto.products


data class Product(
    var available: Boolean?,
    var color: String?,
    var colorCode: String?,
    var description: String?,
    var id: Int?,
    var imageURL: String?,
    var longDescription: String?,
    var name: String?,
    var price: Price?,
    var rating: Double?,
    var releaseDate: Int?,
    var type: String?
)