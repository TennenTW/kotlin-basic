package com.thoughtworks.kotlin_basic.model

import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName("id")
    val id: String?,
    @SerializedName("SKU")
    val sku: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: Double = 0.0,
    @SerializedName("type")
    val type: ProductType?,
    @SerializedName("image")
    val image: String?,
) {
    enum class ProductType(val type: String) {
        NORMAL("NORMAL"),
        HIGH_DEMAND("HIGH_DEMAND"),
    }
}