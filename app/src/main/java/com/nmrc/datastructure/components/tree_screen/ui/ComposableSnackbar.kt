package com.nmrc.datastructure.components.tree_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmrc.datastructure.ui.theme.DarkGrey
import com.nmrc.datastructure.ui.theme.LightGrey
import com.nmrc.datastructure.ui.theme.roboto

@Composable
fun ComposableSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.fillMaxWidth(),
                content = {
                    Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                        Text(
                            text = "Error",
                            color = Red,
                            textAlign = TextAlign.Left,
                            fontFamily = roboto,
                            fontSize = 25.sp
                        )
                        Text(
                            text = data.message,
                            color = LightGrey,
                            textAlign = TextAlign.Left,
                            fontFamily = roboto,
                            fontWeight = Light,
                            fontSize = 20.sp
                        )
                    }
                },
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .background(DarkGrey)
            .shadow(5.dp)
            .wrapContentHeight(Alignment.Bottom)
    )
}