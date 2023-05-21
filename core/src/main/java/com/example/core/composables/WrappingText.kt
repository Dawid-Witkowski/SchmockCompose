package com.example.core.composables

import androidx.compose.ui.graphics.Color
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun WrappingText(
    textToDisplay: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.Black
) {
    Text(
        modifier = modifier,
        text = textToDisplay,
        color = color,
        overflow = TextOverflow.Ellipsis,
        fontWeight = fontWeight,
        maxLines = 1
    )
}