package com.thoughtworks.kotlin_basic.view_model

class ProductViewModel(
    private val sku: String?,
    private val name: String?,
    private val price: Double?,
    private val stock: Int?,
    private val image: String?,
) {
    fun toDisplayString(): String {
        return "SKU: ${sku}, name: ${name}, price: ${price}, stock: ${stock}, image: ${image};"
    }
}