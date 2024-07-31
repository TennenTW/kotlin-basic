package com.thoughtworks.kotlin_basic.viewmodel

import com.thoughtworks.kotlin_basic.model.Inventory
import com.thoughtworks.kotlin_basic.model.Product
import com.thoughtworks.kotlin_basic.model.ProductItem
import com.thoughtworks.kotlin_basic.service.ProductService
import kotlinx.coroutines.*

class ProductViewModel() {
    var products: List<Product>? = null
    var inventories: List<Inventory>? = null
    var productItems: List<ProductItem>? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError(throwable.localizedMessage)
    }

    private fun onError(message: String) {
        println("Exception: $message")
    }

    private fun getProducts(): Deferred<Unit> {
        return CoroutineScope(Dispatchers.IO).async {
            try {
                val response = ProductService.apiService.getProducts()
                products = response.body()
            } catch (e: Exception) {
                onError(e.localizedMessage)
            }
        }
    }

    private fun getInventories(): Deferred<Unit> {
        return CoroutineScope(Dispatchers.IO).async {
            try {
                val response = ProductService.apiService.getInventories()
                inventories = response.body()
            } catch (e: Exception) {
                onError(e.localizedMessage)
            }
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

    fun aggregateProducts() {
        if (products == null || inventories == null) {
            throw Exception("Required data is not provided")
        }
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

    fun getProductWithInventories() {
        runBlocking {
            awaitAll(
                getProducts(),
                getInventories(),
            )
            aggregateProducts()
        }
    }

}