package com.example.featureproductlist.productList

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.Card
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.core.Const
import com.example.core.extensions.toCurrencyString
import com.example.coredata.data.models.appproduct.Product
import com.example.coretheme.ui.composables.BoxWithLoadingIndicator
import com.example.coretheme.ui.composables.WrappingText
import com.example.coretheme.ui.theme.RoundedCornerShapeWithCurvature
import com.example.featureproductlist.R
import com.example.featureproductlist.SharedProductViewModel

@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SharedProductViewModel
) {
    val state = viewModel.state.value

    val scrollState = rememberLazyGridState()

    if (!state.error.isNullOrEmpty()) {
        Toast.makeText(
            LocalContext.current,
            state.error ?: stringResource(R.string.unexpectedError),
            Toast.LENGTH_SHORT
        ).show()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if (state.isLoading) {
            BoxWithLoadingIndicator()
        } else {
            TopAppBar(onSearchChanged = {
                viewModel.searchProductList(it)
            })
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                state = scrollState,
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.currentlyDisplayedProductList) { productToDisplay ->
                    ProductItem(product = productToDisplay,
                        modifier = Modifier.fillMaxWidth(),
                        onProductClick = {
                            viewModel.selectProductToDisplay(productToDisplay)
                            navController.navigate(Const.productDetailScreen)
                        },
                        onFavouritesClick = { /* todo: faviourites */ }
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    onProductClick: () -> Unit,
    onFavouritesClick: (Product) -> Unit
) {
    val heartInteractionSource = remember { MutableInteractionSource() }
    Card(
        modifier = modifier
            .clickable { onProductClick() },
        shape = RoundedCornerShapeWithCurvature.medium,
        elevation = 3.dp
    ) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
                .padding(12.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(200.dp)
                    .height(230.dp),
                model = product.images.first(),
                contentDescription = product.title,
                placeholder = painterResource(id = com.example.coretheme.R.drawable.schmock_logo),
                contentScale = ContentScale.Fit
            )
            Row(modifier = Modifier.align(Alignment.TopEnd)) {
                Icon(
                    // removes the gray BOX that looks ugly AF on click
                    // (I guess clipping the source probably would work as well, but I don't see a reason to do
                    // it on click, cause the heart will get replaced with a filled one)
                    modifier = Modifier.clickable(
                        interactionSource = heartInteractionSource,
                        indication = null
                    ) { onFavouritesClick(product) },
                    painter = painterResource(id = com.example.coretheme.R.drawable.ic_heart),
                    contentDescription = stringResource(
                        id = R.string.addToFavourites,
                        product.title
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {
                WrappingText(text = product.title)
                Text(text = product.price.toCurrencyString())
            }
        }
    }
}

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
