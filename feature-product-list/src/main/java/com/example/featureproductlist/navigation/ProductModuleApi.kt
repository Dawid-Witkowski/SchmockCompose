package com.example.featureproductlist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.core.navigation.FeatureApi

interface ProductModuleApi : FeatureApi

class ProductModuleApiImpl : ProductModuleApi {
    override fun registerGraph(navController: NavController, navGraphBuilder: NavGraphBuilder) {
        InternalProductFeatureApi.registerGraph(
            navController = navController,
            navGraphBuilder = navGraphBuilder
        )
    }
}
