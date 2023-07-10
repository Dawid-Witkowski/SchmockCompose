package com.example.coretheme.ui.composables

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun RawImage(
    modifier: Modifier = Modifier,
    @RawRes resource: Int,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(resource)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale
    )
}
