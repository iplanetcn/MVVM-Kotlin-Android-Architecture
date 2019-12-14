package com.task.data.remote.dto.giphy


import com.google.gson.annotations.SerializedName

data class Pagination(
    var count: Int?,
    var offset: Int?,
    @SerializedName("total_count")
    var totalCount: Int?
)