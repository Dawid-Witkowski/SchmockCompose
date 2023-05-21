package com.example.schmockcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coretheme.ui.theme.SchmockComposeTheme
import com.example.featureproductlist.productList.ProductListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // I don't think I will be adding dark theme (at least for now)
            SchmockComposeTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val mainNavController = rememberNavController()
                    NavHost(navController = mainNavController, startDestination = "product_list_screen") {
                        composable("product_list_screen") { ProductListScreen(navController = mainNavController) }
                    }
                }
            }
        }
    }
}
