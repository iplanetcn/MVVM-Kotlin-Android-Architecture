package com.task.data.remote.dto.giphy


import com.google.gson.annotations.SerializedName

data class Gify(
        @SerializedName("data")
        var info: List<Data?>?,
        var meta: Meta?,
        var pagination: Pagination?
)