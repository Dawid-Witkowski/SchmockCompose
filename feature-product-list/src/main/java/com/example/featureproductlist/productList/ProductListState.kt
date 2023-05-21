package com.example.featureproductlist.productList

import com.example.coredata.data.models.appproduct.Product

data class ProductListState(
    val listOfProductsToDisplay: List<Product>,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String? = null
)
