package com.example.featureproductlist.productList

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.core.composables.BoxWithLoadingIndicator
import com.example.core.composables.WrappingText
import com.example.core.extensions.toCurrencyString
import com.example.coredata.data.models.apiproduct.Rating
import com.example.coredata.data.models.appproduct.Product
import com.example.coretheme.ui.theme.schmockPurple
import com.example.featureproductlist.R

@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ProductListViewModel = hiltViewModel()
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
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }

    }
}

@Composable
fun ProductItem(product: Product, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .width(200.dp)
                .height(250.dp),
            model = product.image,
            contentDescription = product.title,
            contentScale = ContentScale.Fit,
            placeholder = painterResource(id = com.example.coretheme.R.drawable.schmock_logo)
        )
        WrappingText(textToDisplay = product.title)
        WrappingText(textToDisplay = product.price.toCurrencyString(), fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier, label: String = "", onSearchChanged: (newSearch: String) -> Unit
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
                trailingIcon = {Icon(painterResource(id = R.drawable.ic_search), null)},
                label = {Text(label)},
                onValueChange = {
                    searchQueryValue = it
                    onSearchChanged(it)
                }
            )
        }
    }
}

@Preview
@Composable
fun ProductPreview() {
    ProductItem(
        Product(
            category = "Duh",
            description = "DUh",
            id = 1,
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            price = 109.95,
            rating = Rating(0, 0.0),
            title = "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet",
            size = "S"
        )
    )
}