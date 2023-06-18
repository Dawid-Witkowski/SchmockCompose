package com.example.coretheme.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.coretheme.R

@Composable
fun AppLogo(modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.schmock_logo),
        contentDescription = stringResource(id = R.string.appName)
    )
}
