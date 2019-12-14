package com.task.data.remote.dto.giphy


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url")
    var avatarUrl: String?,
    @SerializedName("banner_image")
    var bannerImage: String?,
    @SerializedName("banner_url")
    var bannerUrl: String?,
    @SerializedName("display_name")
    var displayName: String?,
    @SerializedName("is_verified")
    var isVerified: Boolean?,
    @SerializedName("profile_url")
    var profileUrl: String?,
    var username: String?
)