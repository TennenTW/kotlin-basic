package com.thoughtworks.kotlin_basic.viewmodel

import com.thoughtworks.kotlin_basic.model.Inventory
import com.thoughtworks.kotlin_basic.model.Product
import com.thoughtworks.kotlin_basic.service.ProductService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel() {
    private var products: List<Product>? = null
    private var inventories: List<Inventory>? = null

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

    fun getStock(inventories: List<Inventory>) {
    }

    fun getPrice(product: Product, inventories: List<Inventory>) {
    }

    private fun aggregateProducts() {
    }

}