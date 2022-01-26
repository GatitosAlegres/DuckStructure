package com.nmrc.datastructure.components.tree_screen.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun ZoomableListener(
    transformListener: (Offset, Offset, Float) -> Unit,
    tapListener: (Offset) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, _ ->
                    transformListener(centroid, pan, zoom)
                }
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        tapListener(it)
                    }
                )
            }
    )
}