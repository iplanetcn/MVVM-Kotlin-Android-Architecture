package com.task.data.remote.service

import com.task.data.remote.dto.images.Images
import retrofit2.Call
import retrofit2.http.GET


interface ProductImages {
    @GET("https://m.mobile.de/svc/a/237089773")
    fun  fetchImages(): Call<Images>
}