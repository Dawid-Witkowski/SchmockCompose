package com.example.featureproductlist.productList

import com.example.coredata.data.models.appproduct.Product

data class ProductListStateHolder(
    val isLoading: Boolean = false,
    val currentlyDisplayedProductList: List<Product> = emptyList(),
    var error: String? = null
)
