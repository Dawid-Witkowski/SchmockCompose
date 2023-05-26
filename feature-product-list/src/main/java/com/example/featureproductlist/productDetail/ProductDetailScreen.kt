package com.example.featureproductlist.productDetail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.core.navigation.NavRoutes
import com.example.coredata.data.models.appproduct.Product
import com.example.featureproductlist.R
import com.example.featureproductlist.SharedProductViewModel
import com.example.featureproductlist.navigation.ProductRoutes

@Composable
fun ProductDetailScreen(navController: NavController, viewModel: SharedProductViewModel) {
    Column() {
        SchmockTopAppBar(modifier = Modifier.fillMaxWidth()) { navController.navigate(ProductRoutes.ProductListScreen.route) }
        Toast.makeText(LocalContext.current, viewModel.productToDisplay?.title, Toast.LENGTH_LONG).show()
    }
}


@Composable
fun SchmockTopAppBar(modifier: Modifier = Modifier, onNavIconClicked: () -> Unit) {
    ConstraintLayout(modifier = modifier) {
        val (navIcon, appIcon) = createRefs()
        Icon(modifier = Modifier
            .width(50.dp)
            .height(50.dp)
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
            modifier = Modifier.constrainAs(appIcon) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            painter = painterResource(id = com.example.coretheme.R.drawable.schmock_logo),
            contentDescription = stringResource(id = R.string.appName)
        )
    }
}