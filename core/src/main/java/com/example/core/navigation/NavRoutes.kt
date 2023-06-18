package com.example.core.navigation

sealed class NavRoutes(
    val route: String
) {
    object FeatureProductListNestedNav : NavRoutes(route = "featureProductListNestedNav")
    object ProductListScreen : NavRoutes(route = "productListScreen")
    object ProductDetailScreen : NavRoutes(route = "productDetailScreen")
}
