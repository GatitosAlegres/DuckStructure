package com.nmrc.datastructure.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.nmrc.datastructure.ui.theme.*
import com.nmrc.datastructure.ui.theme.BlueVariantDark

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    onSelectionChanged: (String, Boolean) -> Unit,
    name: String = "Chip",
    content: String,
    isDark: Boolean = isSystemInDarkTheme()
) {

    var isSelected by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = modifier.padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) Yellow.copy(red = 0.9f) else BlueVariant
    ) {
        Row(modifier = Modifier
            .padding(8.dp)
            .toggleable(
                value = isSelected,
                onValueChange = {
                    isSelected = !isSelected
                    onSelectionChanged(name, isSelected)
                }
            )
        ) {
            Text(
                text = content,
                style = TextStyle(color = if(isSelected) BlueDark else White )
            )
            if(isSelected) {
                Icon(
                    Icons.Rounded.Done,
                    contentDescription = "",
                    tint = if (isSelected) BlueVariant else Yellow.copy(red = 0.9f))
            }
        }
    }
}