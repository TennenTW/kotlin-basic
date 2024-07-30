package com.thoughtworks.kotlin_basic.model

class ProductItem (
    private val sku: String,
    private val name: String,
    private val price: Double,
    private val stock: Int,
    private val image: String,
) {
    fun getDisplayString(): String {
        return "SKU: ${sku}, name: ${name}, price: ${price}, stock quantity: ${stock}, image URL: ${image};"
    }
}