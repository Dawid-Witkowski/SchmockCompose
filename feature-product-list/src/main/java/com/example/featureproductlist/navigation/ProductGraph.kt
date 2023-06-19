package com.example.featureproductlist.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.featureproductlist.SharedProductViewModel
import com.example.featureproductlist.productDetail.ProductDetailScreen
import com.example.featureproductlist.productList.ProductListScreen

fun NavGraphBuilder.productGraph(
    navController: NavController
) {
    navigation(
        startDestination = ProductRoutes.ProductListScreen.route,
        route = ProductRoutes.ProductGraph.route
    ) {
        composable(route = ProductRoutes.ProductListScreen.route) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(ProductRoutes.ProductGraph.route)
            }
            // Returns an existing HiltViewModel
            // -annotated ViewModel or creates a new one scoped to the current navigation graph
            // present on the {@link NavController} back stack.
            val viewModel = hiltViewModel<SharedProductViewModel>(parentEntry)
            ProductListScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = ProductRoutes.ProductDetailScreen.route
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(ProductRoutes.ProductGraph.route)
            }
            val viewModel = hiltViewModel<SharedProductViewModel>(parentEntry)
            ProductDetailScreen(navController = navController, viewModel = viewModel)
        }
    }
}
