package com.nmrc.datastructure.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
    subtitleModifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    content : @Composable RowScope.() -> Unit = {},
    titleColor: Color = MaterialTheme.colors.onBackground,
    subTitleColor: Color = Green) {
    Text(
        text = title,
        modifier = modifier.padding(16.dp),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = titleColor
        )
    )

    Row {
        content()
    }

    Text(
        text = subtitle,
        modifier = subtitleModifier.padding(16.dp),
        style = TextStyle(
            fontSize = 16.sp,
            color = subTitleColor
        )
    )
}