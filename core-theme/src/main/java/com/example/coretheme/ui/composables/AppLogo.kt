package com.example.coretheme.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.coretheme.R

@Composable
fun AppLogo(modifier: Modifier) {
    Image(
        modifier = modifier
            .width(50.dp)
            .height(50.dp),
        painter = painterResource(id = R.drawable.schmock_logo),
        contentDescription = stringResource(id = R.string.appName)
    )
}
