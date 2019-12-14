package com.task.data.remote.dto.giphy


import com.google.gson.annotations.SerializedName

data class GifsData(
        @SerializedName("data")
        var gifsList: List<Gif?>?,
        var meta: Meta?,
        var pagination: Pagination?
)