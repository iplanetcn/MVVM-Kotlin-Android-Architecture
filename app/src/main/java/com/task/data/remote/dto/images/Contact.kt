package com.task.data.remote.dto.images


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