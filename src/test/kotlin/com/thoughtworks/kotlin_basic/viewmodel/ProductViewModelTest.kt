package com.thoughtworks.kotlin_basic.viewmodel

import com.thoughtworks.kotlin_basic.exceptions.MaxSizeExceededException
import com.thoughtworks.kotlin_basic.model.Inventory
import com.thoughtworks.kotlin_basic.model.Product
import com.thoughtworks.kotlin_basic.util.ColumnUtil
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ProductViewModelTest {
    private val viewModel = ProductViewModel()
    private val products = listOf(
        Product(
            id = "1",
            sku = "ABC123",
            name = "Electronic Watch",
            price = 299.99,
            type = Product.ProductType.NORMAL,
            image = "image1.jpg",
        ),
        Product(
            id = "2",
            sku = "DEF456",
            name = "Sports Shoes",
            price = 499.6,
            type = Product.ProductType.NORMAL,
            image = "image2.jpg",
        ),
        Product(
            id = "3",
            sku = "GHI789",
            name = "Book \"Exploring the Universe\"",
            price = 89.0,
            type = Product.ProductType.NORMAL,
            image = "image3.jpg",
        ),
        Product(
            id = "4",
            sku = "JKL012",
            name = "Bluetooth Headphones",
            price = 199.0,
            type = Product.ProductType.HIGH_DEMAND,
            image = "image4.jpg",
        ),
        Product(
            id = "5",
            sku = "MNO345",
            name = "Smart Band",
            price = 149.45,
            type = Product.ProductType.HIGH_DEMAND,
            image = "image5.jpg",
        )
    )
    private val inventories = listOf(
        Inventory(
            id = "1",
            sku = "ABC123",
            zone = "CN_NORTH",
            quantity = 120,
        ),
        Inventory(
            id = "2",
            sku = "ABC123",
            zone = "US_WEST",
            quantity = 80,
        ),
        Inventory(
            id = "3",
            sku = "DEF456",
            zone = "EU_CENTRAL",
            quantity = 200,
        ),
        Inventory(
            id = "4",
            sku = "GHI789",
            zone = "CN_EAST",
            quantity = 150,
        ),
        Inventory(
            id = "5",
            sku = "JKL012",
            zone = "CN_SOUTH",
            quantity = 10,
        ),
        Inventory(
            id = "6",
            sku = "JKL012",
            zone = "US_EAST",
            quantity = 20,
        ),
        Inventory(
            id = "7",
            sku = "MNO345",
            zone = "CN_NORTH",
            quantity = 65,
        ),
        Inventory(
            id = "8",
            sku = "MNO345",
            zone = "AU_SOUTH",
            quantity = 12,
        ),
        Inventory(
            id = "9",
            sku = "MNO345",
            zone = "AU_SOUTH",
            quantity = 50,
        ),
    )

    @Test
    fun `The stock of a product equals the sum of stocks in various regions`() {
        val productInventories = inventories.filter { it.sku == "ABC123" }
        assertEquals(200, viewModel.getStock(productInventories))
    }

    @Test
    fun `If the product is normal (type is NORMAL), the actual price is equal to the original price`() {
        val productInventories = inventories.filter { it.sku == "ABC123" }
        assertEquals(299.99, viewModel.getPrice(products[0], productInventories))
    }

    @Test
    fun `If the product is high-demand (type is HIGH_DEMAND), the stock is greater than 100, the price is equal to the original price`() {
        val product = Product(
            id = "4",
            sku = "JKL012",
            name = "Bluetooth Headphones",
            price = 199.0,
            type = Product.ProductType.HIGH_DEMAND,
            image = "image4.jpg",
        );
        val productInventories = listOf(
            Inventory(
                id = "5",
                sku = "JKL012",
                zone = "CN_SOUTH",
                quantity = 50,
            ),
            Inventory(
                id = "6",
                sku = "JKL012",
                zone = "US_EAST",
                quantity = 60,
            ),
        )
        assertEquals(199.0, viewModel.getPrice(product, productInventories))
    }

    @Test
    fun `If the product is high-demand (type is HIGH_DEMAND), the stock is equal to 101, the price is equal to the original price`() {
        val product = Product(
            id = "4",
            sku = "JKL012",
            name = "Bluetooth Headphones",
            price = 199.0,
            type = Product.ProductType.HIGH_DEMAND,
            image = "image4.jpg",
        );
        val productInventories = listOf(
            Inventory(
                id = "5",
                sku = "JKL012",
                zone = "CN_SOUTH",
                quantity = 50,
            ),
            Inventory(
                id = "6",
                sku = "JKL012",
                zone = "US_EAST",
                quantity = 51,
            ),
        )
        assertEquals(199.0, viewModel.getPrice(product, productInventories))
    }

    @Test
    fun `If the product is high-demand (type is HIGH_DEMAND), the stock is equal to 100, the price is 120 percent of the original price`() {
        val product = Product(
            id = "4",
            sku = "JKL012",
            name = "Bluetooth Headphones",
            price = 199.0,
            type = Product.ProductType.HIGH_DEMAND,
            image = "image4.jpg",
        );
        val productInventories = listOf(
            Inventory(
                id = "5",
                sku = "JKL012",
                zone = "CN_SOUTH",
                quantity = 50,
            ),
            Inventory(
                id = "6",
                sku = "JKL012",
                zone = "US_EAST",
                quantity = 50,
            ),
        )
        assertEquals(238.8, viewModel.getPrice(product, productInventories))
    }

    @Test
    fun `If the product is high-demand (type is HIGH_DEMAND), and the stock is less than 100 and greater than 30, the price is 120 percent of the original price`() {
        val product = Product(
            id = "4",
            sku = "JKL012",
            name = "Bluetooth Headphones",
            price = 199.0,
            type = Product.ProductType.HIGH_DEMAND,
            image = "image4.jpg",
        );
        val productInventories = listOf(
            Inventory(
                id = "5",
                sku = "JKL012",
                zone = "CN_SOUTH",
                quantity = 50,
            ),
            Inventory(
                id = "6",
                sku = "JKL012",
                zone = "US_EAST",
                quantity = 49,
            ),
        )
        assertEquals(238.8, viewModel.getPrice(product, productInventories))
    }

    @Test
    fun `If the product is high-demand (type is HIGH_DEMAND), and the stock is equal to 31, the price is 120 percent of the original price`() {
        val product = Product(
            id = "4",
            sku = "JKL012",
            name = "Bluetooth Headphones",
            price = 199.0,
            type = Product.ProductType.HIGH_DEMAND,
            image = "image4.jpg",
        );
        val productInventories = listOf(
            Inventory(
                id = "5",
                sku = "JKL012",
                zone = "CN_SOUTH",
                quantity = 15,
            ),
            Inventory(
                id = "6",
                sku = "JKL012",
                zone = "US_EAST",
                quantity = 16,
            ),
        )
        assertEquals(238.8, viewModel.getPrice(product, productInventories))
    }

    @Test
    fun `If the product is high-demand (type is HIGH_DEMAND), the stock is equal to 30, the price is 150 percent of the original price`() {
        val product = Product(
            id = "4",
            sku = "JKL012",
            name = "Bluetooth Headphones",
            price = 199.0,
            type = Product.ProductType.HIGH_DEMAND,
            image = "image4.jpg",
        );
        val productInventories = listOf(
            Inventory(
                id = "5",
                sku = "JKL012",
                zone = "CN_SOUTH",
                quantity = 15,
            ),
            Inventory(
                id = "6",
                sku = "JKL012",
                zone = "US_EAST",
                quantity = 15,
            ),
        )
        assertEquals(298.5, viewModel.getPrice(product, productInventories))
    }

    @Test
    fun `If the product is high-demand (type is HIGH_DEMAND), the stock is less than 30, the price is 150 percent of the original price`() {
        val product = Product(
            id = "4",
            sku = "JKL012",
            name = "Bluetooth Headphones",
            price = 199.0,
            type = Product.ProductType.HIGH_DEMAND,
            image = "image4.jpg",
        );
        val productInventories = listOf(
            Inventory(
                id = "5",
                sku = "JKL012",
                zone = "CN_SOUTH",
                quantity = 15,
            ),
            Inventory(
                id = "6",
                sku = "JKL012",
                zone = "US_EAST",
                quantity = 14,
            ),
        )
        assertEquals(298.5, viewModel.getPrice(product, productInventories))
    }

}