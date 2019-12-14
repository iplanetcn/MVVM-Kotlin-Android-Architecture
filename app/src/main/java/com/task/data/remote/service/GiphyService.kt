package com.task.data.remote.service

import com.task.data.remote.dto.giphy.Gify
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GiphyService {
    @GET("gifs/trending")
    fun getGiphy(@Query("limit") limit: Int = 25, @Query("rating") rating: String = "G"): Call<Gify>
}