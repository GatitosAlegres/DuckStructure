package com.nmrc.datastructure.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nmrc.datastructure.ui.theme.Aqua
import com.nmrc.datastructure.ui.theme.BlueDarkMaterial
import com.nmrc.datastructure.ui.theme.WhiteMaterial

@Composable
fun CardDetail(
    content: @Composable ColumnScope.() -> Unit,
    actionContent: @Composable BoxScope.() -> Unit = {},
    detailContent: @Composable BoxScope.() -> Unit = {},
    isDark: Boolean = isSystemInDarkTheme()
) {

    val color = if (isDark) Aqua else WhiteMaterial

    Column(modifier = Modifier
        .background(color)
        .padding(16.dp)) {

        content()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .padding(4.dp),
            contentAlignment = Alignment.CenterStart
        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                content = detailContent
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                content = actionContent
            )
        }
    }
}