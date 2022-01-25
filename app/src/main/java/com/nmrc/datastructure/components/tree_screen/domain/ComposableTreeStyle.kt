package com.nmrc.datastructure.components.tree_screen.domain

import androidx.compose.ui.graphics.Color
import com.nmrc.datastructure.ui.theme.LightGrey

data class ComposableTreeStyle(
    var theme: ComposableTreeTheme = ComposableTreeTheme.getThemes()[0],
    var nodeSize: Float = 80f,
    var ySpacing: Float = 5f,
    var lineWidth: Float = 60f,
    val textColor: Color = LightGrey
)
