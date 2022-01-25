package com.nmrc.datastructure.components.tree_screen.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.nmrc.datastructure.ui.theme.Dark
import com.nmrc.datastructure.ui.theme.LightGrey
import com.nmrc.datastructure.ui.theme.White
import com.nmrc.datastructure.ui.theme.roboto

@Composable
fun ComposableEmptyTree(
    boxWithConstraintsScope: BoxWithConstraintsScope,
    isDark: Boolean = isSystemInDarkTheme()) {
    // Box with constraints used to access max width and max height
    // Calculate the size of the composable such that it fits within constraints
    val respWidth = (LocalDensity.current.run {
        minOf(
            boxWithConstraintsScope.maxWidth.toPx(),
            boxWithConstraintsScope.maxHeight.toPx()
        )
    } / 2f) * 0.4f

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Lista de Medicamentos Vacia",
            fontSize = 25.sp,
            fontFamily = roboto,
            textAlign = TextAlign.Center,
            color = if(isDark) White else Dark
        )
        Text(
            text = "Inserte un medicamento",
            fontSize = 20.sp,
            fontFamily = roboto,
            textAlign = TextAlign.Center,
            color = if(isDark) White else Dark
        )
    }
}