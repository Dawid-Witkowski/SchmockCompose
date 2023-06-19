package com.example.coretheme.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp

// a round button with a border that indicates the selected state
@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: (String) -> Unit
) {
    Box(
        modifier = Modifier.size(60.dp),
        contentAlignment = Alignment.Center
    ) {
        OutlinedButton(
            modifier = modifier
                .size(50.dp),
            onClick = { onClick(text) },
            shape = CircleShape,
            elevation = ButtonDefaults.elevation(defaultElevation = 8.dp),
            border = BorderStroke(width = 1.dp, color = if (selected) Color.Black else LightGray)
        ) {
            Text(
                text = text,
                color = Color.Black
            )
        }
    }
}
