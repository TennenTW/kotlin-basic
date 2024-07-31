package com.thoughtworks.kotlin_basic.view

import com.thoughtworks.kotlin_basic.viewmodel.ProductViewModel

object ProductView {
    private val productViewModel = ProductViewModel()

    fun showProductsInfo() {
        productViewModel.getProductWithInventories()
        productViewModel.productItems?.forEach {
            println(it.getDisplayString())
        }
    }
}