package com.nmrc.datastructure.components.tree_screen.domain

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


interface Dropdownable {
    @Composable
    fun Thumbnail() {
        Text(
            text = "$this",
            color = Color.White
        )
    }
}