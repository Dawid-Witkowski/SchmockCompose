package com.example.schmockcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.coretheme.ui.theme.SchmockComposeTheme
import com.example.featureproductlist.navigation.ProductRoutes
import com.example.featureproductlist.navigation.productGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // I don't think I will be adding dark theme (at least for now)
            SchmockComposeTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val mainNavController = rememberNavController()
                    NavHost(
                        navController = mainNavController,
                        startDestination = ProductRoutes.ProductGraph.route
                    ) {
                        productGraph(navController = mainNavController)
                    }
                }
            }
        }
    }
}
