package com.example.schmockcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.core.Const

@Composable
fun AppNavGraph(
    navController: NavHostController,
    navigationProvider: NavigationProvider
) {
    NavHost(navController = navController, startDestination = Const.nestedProductRoute) {
        navigationProvider.productModule.registerGraph(
            navController = navController,
            navGraphBuilder = this
        )
    }
}
