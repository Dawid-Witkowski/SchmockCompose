package com.example.schmockcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.coretheme.ui.theme.SchmockComposeTheme
import com.example.schmockcompose.navigation.AppNavGraph
import com.example.schmockcompose.navigation.NavigationProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationProvider: NavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // I don't think I will be adding dark theme (at least for now)
            SchmockComposeTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val appNavController = rememberNavController()
                    AppNavGraph(navController = appNavController, navigationProvider = navigationProvider)
                }
            }
        }
    }
}
