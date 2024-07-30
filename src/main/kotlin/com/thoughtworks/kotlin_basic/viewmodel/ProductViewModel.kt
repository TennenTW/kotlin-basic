package com.thoughtworks.kotlin_basic.viewmodel

import com.thoughtworks.kotlin_basic.model.Inventory
import com.thoughtworks.kotlin_basic.model.Product
import com.thoughtworks.kotlin_basic.model.ProductItem
import com.thoughtworks.kotlin_basic.service.ProductService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel() {
    private var products: List<Product>? = null
    private var inventories: List<Inventory>? = null
    private var productItems: List<ProductItem>? = null

    private fun getProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = ProductService.apiService.getProducts()
            products = response.body()
        }
    }

    private fun getInventories() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = ProductService.apiService.getInventories()
            inventories = response.body()
        }
    }

    private fun getPricePercentageByStockForHighDemand(stock: Int): Double {
        return when (stock) {
            in 0..30 -> 1.5
            in 31 .. 100 -> 1.2
            else -> 1.0
        }
    }

    fun getProductPrice(product: Product, stock: Int): Double {
        val percentage = when (product.type) {
            Product.ProductType.HIGH_DEMAND -> getPricePercentageByStockForHighDemand(stock)
            else -> 1.0
        }
        return product.price.toBigDecimal().multiply(percentage.toBigDecimal()).toDouble()
    }

    fun getStockByInventories(inventories: List<Inventory>): Int {
        return inventories.sumOf { it.quantity ?: 0 }
    }

    private fun aggregateProducts() {
        val inventoriesMapBySKU = inventories?.groupBy { it.sku ?: "" }
        productItems = products?.map {
            val sku = it.sku ?: ""
            val stock = getStockByInventories(inventoriesMapBySKU?.get(sku) ?: listOf())
            ProductItem(
                name = it.name ?: "",
                sku = sku,
                price = getProductPrice(it, stock),
                stock = stock,
                image = it.image ?: ""
            )
        }
    }

}