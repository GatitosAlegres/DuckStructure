package com.nmrc.datastructure.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun ActionIconBottom(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    tint: Color,
    content: String,
    onClick: () -> Unit
) {

    val color = tint.copy(alpha = 0.1f)

    TextButton(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color),
        onClick = onClick
    ) {
        Text(
            text = content,
            style = TextStyle(color = tint)
        )

        Icon(
            icon,
            tint = tint,
            contentDescription = ""
        )
    }
}