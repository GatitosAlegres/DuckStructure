package com.nmrc.datastructure.components.tree_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmrc.datastructure.ui.theme.DarkGrey
import com.nmrc.datastructure.ui.theme.LightBlue
import com.nmrc.datastructure.ui.theme.LightGrey
import com.nmrc.datastructure.ui.theme.roboto

@ExperimentalUnitApi
@Composable
fun ComposableBottomBar(
    selected: Int,
    onReset: () -> Unit,
    onRemove: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(3.dp)
            .background(DarkGrey)
            .padding(10.dp)
    ) {
        val maxWidth = this.maxWidth
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Replay,
                    null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .size(40.dp)
                        .padding(top = 0.dp, end = 10.dp)
                        .clickable {
                            onReset()
                        },
                    tint = Red
                )


                Text(
                    text = when {
                        maxWidth > 480.dp -> if (selected != -1) "$selected Selected" else "Tap to Select Node"
                        else -> if (selected != -1) "$selected" else "Tap to Select"
                    },
                    fontFamily = roboto,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    fontSize = 25.sp,
                    letterSpacing = 0.sp,
                    color = LightGrey,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 10.dp)
                )
                if (selected != -1) {
                    Text(
                        text = "Remove",
                        fontFamily = roboto,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal,
                        fontSize = 25.sp,
                        letterSpacing = 0.sp,
                        color = LightBlue,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(end = 10.dp)
                            .clickable {
                                onRemove()
                            }
                    )
                }
            }
            Row(
                modifier = Modifier.weight(0.2f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

            }
        }
    }
}