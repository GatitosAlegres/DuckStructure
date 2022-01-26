package com.nmrc.datastructure.components.tree_screen.domain


import androidx.compose.foundation.Canvas
import androidx.compose.ui.res.imageResource
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmrc.datastructure.ui.theme.*

// This is selectable from a drop-down, thus implements dropdownable
// Overrides .Thumbnail to display a picture Thumbnail
data class ComposableTreeTheme(
    val nodeColor: Color,
    val lineColor: Color,
    val selectedNodeColor: Color,
    // Used to make a more complicated Thumbnail with Canvas if desired
    val imageId: Int = -1,
    val selectedImageId: Int = -1

) : Dropdownable {
    @Composable
    override fun Thumbnail() {
        val theme = this
        Box(
            modifier = Modifier
                .shadow(3.dp)
        ) {
            Text(
                text = "C",
                fontSize = 0.sp,
                color = theme.lineColor,
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp)
                    .background(theme.lineColor)
            )
            if (imageId != -1) {
                val image = ImageBitmap.imageResource(id = imageId)
                val aspectRatio = (image.width.toFloat()) / image.height

                Canvas(
                    modifier = Modifier
                        .width(25.dp)
                        .height(25.dp),
                ) {
                    drawImage(
                        image = image,
                        dstSize = IntSize(
                            width = (25.dp.toPx() * aspectRatio).toInt(),
                            height = (25.dp.toPx() * aspectRatio).toInt()
                        )
                    )
                }
            }
        }
    }

    companion object {
        fun getThemes(): List<ComposableTreeTheme> {
            return listOf(
                // BLUE
                ComposableTreeTheme(
                    Green, // Circle
                    Aqua,                          // Linear
                    GreenDark, // on Select
                )
            )
        }
    }
}