package com.nmrc.datastructure.components

import androidx.compose.foundation.isSystemInDarkTheme
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
import com.nmrc.datastructure.ui.theme.BlueVariant
import com.nmrc.datastructure.ui.theme.Green

@Composable
fun Header(
    modifier: Modifier = Modifier,
    subtitleModifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    weightSubtitle: FontWeight = FontWeight.Normal,
    titleColor: Color = MaterialTheme.colors.onBackground,
    isDark: Boolean = isSystemInDarkTheme()) {
    Text(
        text = title,
        modifier = modifier.padding(16.dp),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = titleColor
        )
    )

    Text(
        text = subtitle,
        modifier = subtitleModifier.padding(16.dp),
        style = TextStyle(
            fontSize = 16.sp,
            color = if (isDark) Green else BlueVariant,
            fontWeight = weightSubtitle
        )
    )
}