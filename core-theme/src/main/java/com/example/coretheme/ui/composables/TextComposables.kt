package com.example.coretheme.ui.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

// got bored of writing overflow everywhere
@Composable
fun WrappingText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = MaterialTheme.typography.body2.fontSize,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = 1
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        overflow = TextOverflow.Ellipsis,
        fontSize = fontSize,
        textAlign = textAlign,
        fontWeight = fontWeight,
        maxLines = maxLines
    )
}
