package com.nmrc.datastructure.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun CardX(
    modifier: Modifier = Modifier,
    title: String,
    painter: Painter,
    description: String,
    onDetail: () -> Unit,
    onAction: () -> Unit
) {

    CardExplained(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp)),
        contentPreview = {

            DefaultPreviewCard(
                painter = painter,
                text = title
            )
        },
        content = {

            Text(
                text = description,
                textAlign = TextAlign.Justify
            )
        },
        detailContent = {

            ActionIconBottom(
                icon = Icons.Rounded.Info,
                tint = Color.Green,
                content = "",
                onClick = onDetail
            )
        },
        actionContent = {

            ActionIconBottom(
                icon = Icons.Outlined.PlayArrow,
                tint = Color.Blue,
                content = "Go",
                onClick = onAction
            )
        }
    )
}