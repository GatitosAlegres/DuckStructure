package com.nmrc.datastructure.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
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
import com.nmrc.datastructure.ui.theme.Blue
import com.nmrc.datastructure.ui.theme.Green
import com.nmrc.datastructure.ui.theme.Orange
import com.nmrc.datastructure.ui.theme.Yellow

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun CardX(
    modifier: Modifier = Modifier,
    isDark: Boolean = isSystemInDarkTheme(),
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
                tint = if(isDark) Yellow else Orange,
                content = "",
                onClick = onDetail
            )
        },
        actionContent = {

            ActionIconBottom(
                icon = Icons.Outlined.PlayArrow,
                tint = if(isDark) Green else Blue,
                content = "Go",
                onClick = onAction
            )
        }
    )
}