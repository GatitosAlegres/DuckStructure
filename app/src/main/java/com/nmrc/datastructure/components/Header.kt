package com.nmrc.datastructure.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmrc.datastructure.ui.theme.BlueMaterial
import com.nmrc.datastructure.ui.theme.Green

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String) {
    Text(
        text = title,
        modifier = modifier.padding(16.dp),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )
    )

    Text(
        text = subtitle,
        modifier = Modifier.padding(16.dp),
        style = TextStyle(
            fontSize = 16.sp,
            color = Green
        )
    )
}