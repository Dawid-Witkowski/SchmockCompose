package com.example.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

// will allow feature-modules to register different nested graphs into the app graph
interface FeatureApi {
    fun registerGraph(
        navController: NavController,
        navGraphBuilder: NavGraphBuilder
    )
}
