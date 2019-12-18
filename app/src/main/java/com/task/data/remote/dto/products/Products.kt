package com.task.data.remote.dto.products


data class Products(
    var filters: List<String?>?,
    var header: Header?,
    var products: List<Product?>?
)