package com.task.data.remote.service

import com.task.data.remote.dto.products.Products
import retrofit2.Call
import retrofit2.http.GET


interface ProductsService {
    @GET("products-test.json")
    fun getProducts(): Call<Products>
}