package com.thoughtworks.kotlin_basic.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductService {
    private const val BASE_URL = "localhost:3000"

    val apiService: ProductApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductApi::class.java)
}