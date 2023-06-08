package com.example.featureproductlist.productDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.coredata.util.SizeEnum
import com.example.coredata.util.getSizesAsList
import com.example.coretheme.ui.composables.CircleButton
import com.example.coretheme.ui.theme.RoundedCornerShapeWithCurvature
import com.example.coretheme.ui.theme.lightGray
import com.example.featureproductlist.R
import com.example.featureproductlist.SharedProductViewModel
import com.example.featureproductlist.navigation.ProductRoutes

@Composable
fun ProductDetailScreen(navController: NavController, viewModel: SharedProductViewModel) {
    var selectedProduct = viewModel.productToDisplay
    Column {
        SchmockTopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) { navController.navigate(ProductRoutes.ProductListScreen.route) }
        Spacer(modifier = Modifier.height(12.dp))
        if (selectedProduct != null) {
            ImageCarousel(
                modifier = Modifier.fillMaxWidth(),
                productImages = selectedProduct.images,
                description = selectedProduct.title
            )
            Spacer(modifier = Modifier.height(12.dp))
            SizeSelectionGroup(
                selectedSizeAtStart = selectedProduct.size
            )
        }
    }
}


@Composable
fun SchmockTopAppBar(modifier: Modifier = Modifier, onNavIconClicked: () -> Unit) {
    ConstraintLayout(modifier = modifier) {
        val (navIcon, appIcon) = createRefs()
        Icon(modifier = Modifier
            .width(25.dp)
            .height(25.dp)
            .constrainAs(navIcon) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            .clickable { onNavIconClicked() },
            painter = painterResource(id = com.example.coretheme.R.drawable.ic_arrow_back),
            contentDescription = stringResource(id = R.string.returnToProductList)
        )
        Image(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .constrainAs(appIcon) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            painter = painterResource(id = com.example.coretheme.R.drawable.schmock_logo),
            contentDescription = stringResource(id = R.string.appName)
        )
    }
}

@Composable
private fun ProductImageCard(
    modifier: Modifier = Modifier,
    linkToImage: String,
    productDescription: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShapeWithCurvature.large,
        border = BorderStroke(width = 1.dp, color = lightGray),
        elevation = 10.dp,
    ) {
        AsyncImage(
            modifier = Modifier
                .width(250.dp)
                .height(350.dp),
            model = linkToImage,
            contentDescription = productDescription,
            contentScale = ContentScale.Fit,
            placeholder = painterResource(id = com.example.coretheme.R.drawable.schmock_logo)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
// I guess it could also take a list of descriptions, but it's one item repeated anyway, so
// forgive me! (It would be easily changeable tho)
private fun ImageCarousel(
    modifier: Modifier = Modifier,
    productImages: List<String>,
    description: String
) {
    val lazyListState = rememberLazyListState()
    LazyRow(
        modifier = modifier,
        state = lazyListState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(items = productImages) { index, link ->
            Spacer(
                // just to get padding between the items, not at the end & start
                modifier = Modifier.width(
                    when (index) {
                        0 -> 0.dp
                        productImages.size -> 0.dp
                        else -> 16.dp
                    }
                )
            )
            ProductImageCard(
                linkToImage = link,
                productDescription = description
            )
        }
    }
}

@Composable
private fun SizeSelectionGroup(selectedSizeAtStart: SizeEnum) {
    var selectedSize by remember { mutableStateOf(selectedSizeAtStart.text) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        getSizesAsList().forEach { sizeSymbol ->
            CircleButton(
                text = sizeSymbol,
                selected = (selectedSize == sizeSymbol),
                onClick = { selectedSize = it }
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}