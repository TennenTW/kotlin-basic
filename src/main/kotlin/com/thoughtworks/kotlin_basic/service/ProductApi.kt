package com.thoughtworks.kotlin_basic.service

import com.thoughtworks.kotlin_basic.model.Inventory
import com.thoughtworks.kotlin_basic.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>

    @GET("inventories")
    suspend fun getInventories(): Response<List<Inventory>>
}