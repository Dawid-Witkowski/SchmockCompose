package com.example.featureproductlist.productList

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.core.extensions.toCurrencyString
import com.example.coredata.data.models.appproduct.Product
import com.example.coretheme.ui.composables.BoxWithLoadingIndicator
import com.example.coretheme.ui.composables.WrappingText
import com.example.featureproductlist.R
import com.example.featureproductlist.SharedProductViewModel
import com.example.featureproductlist.navigation.ProductRoutes

@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SharedProductViewModel
) {
    val pokemonList by remember { viewModel.productList }
    val loadError by remember { viewModel.loadErrorMessage }
    val isLoading by remember { viewModel.isLoading }

    val scrollState = rememberLazyGridState()

    // an error occurred, display the message
    if (loadError.isNotBlank()) {
        Toast.makeText(LocalContext.current, loadError, Toast.LENGTH_LONG).show()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if (isLoading) {
            BoxWithLoadingIndicator()
        } else {
            TopAppBar(onSearchChanged = { viewModel.searchProductList(it) })
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                state = scrollState,
                columns = GridCells.Fixed(2)
            ) {
                items(pokemonList) { productToDisplay ->
                    ProductItem(
                        product = productToDisplay,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        onProductClick = {
                            viewModel.selectProductToDisplay(productToDisplay)
                            navController.navigate(ProductRoutes.ProductDetailScreen.route)
                        }
                    )
                }
            }
        }
    }
}

// used only here, no need to make it public
@Composable
private fun ProductItem(product: Product, modifier: Modifier = Modifier, onProductClick: () -> Unit) {
    Column(
        modifier = modifier.clickable { onProductClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .width(200.dp)
                .height(250.dp),
            model = product.images.first(),
            contentDescription = product.title,
            contentScale = ContentScale.Fit,
            placeholder = painterResource(id = com.example.coretheme.R.drawable.schmock_logo)
        )
        WrappingText(text = product.title)
        WrappingText(text = product.price.toCurrencyString(), fontWeight = FontWeight.Bold)
    }
}

// not sure whether making private composables is a good practise, but I will try to separate
// commonly used composables outside of a screen "class"
@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
    label: String = "",
    onSearchChanged: (newSearch: String) -> Unit
) {
    var searchQueryValue by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Image(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp),
                painter = painterResource(id = com.example.coretheme.R.drawable.schmock_logo),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
        Row {
            OutlinedTextField(
                value = searchQueryValue,
                trailingIcon = { Icon(painterResource(id = R.drawable.ic_search), null) },
                label = { Text(label) },
                onValueChange = {
                    searchQueryValue = it
                    onSearchChanged(it)
                }
            )
        }
    }
}
