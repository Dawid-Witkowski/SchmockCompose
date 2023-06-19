package com.example.featureproductlist.productDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.core.extensions.toCurrencyString
import com.example.coredata.data.models.appproduct.Product
import com.example.coredata.util.SizeEnum
import com.example.coredata.util.getSizesAsList
import com.example.coretheme.ui.composables.AppLogo
import com.example.coretheme.ui.composables.CircleButton
import com.example.coretheme.ui.theme.RoundedCornerShapeWithCurvature
import com.example.coretheme.ui.theme.lightGray
import com.example.featureproductlist.R
import com.example.featureproductlist.SharedProductViewModel
import com.example.featureproductlist.navigation.ProductRoutes

@Composable
fun ProductDetailScreen(navController: NavController, viewModel: SharedProductViewModel) {
    val selectedProduct: Product = viewModel.productToDisplay

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        SchmockTopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) { navController.navigate(ProductRoutes.ProductListScreen.route) }
        Spacer(modifier = Modifier.height(12.dp))
        // product images
        ImageCarousel(
            modifier = Modifier.fillMaxWidth(),
            productImages = selectedProduct.images,
            description = selectedProduct.title
        )
        Spacer(modifier = Modifier.height(12.dp))
        SizeSelectionGroup(
            selectedSizeAtStart = selectedProduct.size
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = selectedProduct.title
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = selectedProduct.price.toCurrencyString(),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 36.dp, vertical = 6.dp),
                text = stringResource(id = R.string.add_to_cart)
            )
        }
    }
}

@Composable
fun SchmockTopAppBar(modifier: Modifier = Modifier, onNavIconClicked: () -> Unit) {
    ConstraintLayout(modifier = modifier) {
        val (navIcon, appIcon) = createRefs()
        Icon(
            modifier = Modifier
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
        AppLogo(
            modifier = Modifier
                .constrainAs(appIcon) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
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
        elevation = 10.dp
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
    val state = rememberPagerState()
    HorizontalPager(
        modifier = modifier,
        // calculating those values for different screen sizes would be best,
        // todo: consider supporting tablets etc.?
        contentPadding = PaddingValues(horizontal = 64.dp),
        pageSpacing = 16.dp,
        pageCount = productImages.size,
        state = state
    ) { index ->
        ProductImageCard(
            linkToImage = productImages[index],
            productDescription = description
        )
    }
}

@Composable
private fun SizeSelectionGroup(modifier: Modifier = Modifier, selectedSizeAtStart: SizeEnum) {
    // just let's the group "handle itself"
    var selectedSize by remember { mutableStateOf(selectedSizeAtStart.text) }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        getSizesAsList().forEach { sizeSymbol ->
            CircleButton(
                text = sizeSymbol,
                selected = (selectedSize == sizeSymbol),
                onClick = { selectedSize = it }
            )
        }
    }
}
