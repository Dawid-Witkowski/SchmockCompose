package com.example.featureproductlist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core.Const
import com.example.core.extensions.sharedViewModel
import com.example.core.navigation.FeatureApi
import com.example.featureproductlist.SharedProductViewModel
import com.example.featureproductlist.productDetail.ProductDetailScreen
import com.example.featureproductlist.productList.ProductListScreen

internal object InternalProductFeatureApi : FeatureApi {
    override fun registerGraph(navController: NavController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            startDestination = Const.productListScreen,
            route = Const.nestedProductRoute
        ) {
            composable(Const.productListScreen) {
                val productViewModel =
                    it.sharedViewModel<SharedProductViewModel>(navController = navController)
                ProductListScreen(navController = navController, viewModel = productViewModel)
            }
            composable(Const.productDetailScreen) {
                val productViewModel =
                    it.sharedViewModel<SharedProductViewModel>(navController = navController)
                ProductDetailScreen(navController = navController, viewModel = productViewModel)
            }
        }
    }
}
