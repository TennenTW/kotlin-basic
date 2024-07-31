package com.thoughtworks.kotlin_basic.view

import com.thoughtworks.kotlin_basic.viewmodel.ProductViewModel

interface ProductDisplayer {
    fun showProductsInfo()
}

object ProductView: ProductDisplayer {
    private val productViewModel = ProductViewModel()

    override fun showProductsInfo() {
        productViewModel.getProductWithInventories()
        productViewModel.productItems?.forEach {
            println(it.getDisplayString())
        }
    }
}