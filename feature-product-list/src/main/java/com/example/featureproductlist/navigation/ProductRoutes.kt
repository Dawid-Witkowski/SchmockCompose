package com.example.featureproductlist.navigation

sealed class ProductRoutes(
    val route: String
) {
    object ProductGraph : ProductRoutes(route = "product")
    object ProductListScreen : ProductRoutes(route = "productListScreen")
    object ProductDetailScreen : ProductRoutes(route = "productDetailScreen")
}
