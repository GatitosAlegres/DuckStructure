package com.nmrc.datastructure.components.tree_screen.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import com.nmrc.datastructure.ui.theme.DarkGrey

@Composable
fun ComposableResetButton(
    modifier: Modifier,
    color: Color = Red,
    onReset: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = {
            onReset()
        },
        contentPadding = PaddingValues(1.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = DarkGrey),
    ) {
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "Reset Tree and Setitngs",
            modifier = Modifier.fillMaxSize(),
            tint = color
        )
    }
}