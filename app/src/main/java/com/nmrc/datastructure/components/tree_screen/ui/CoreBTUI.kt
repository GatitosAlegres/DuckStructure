package com.nmrc.datastructure.components.tree_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.nmrc.datastructure.components.tree_screen.domain.*
import com.nmrc.datastructure.ui.theme.BlueVariantAlt
import com.nmrc.datastructure.ui.theme.GrayLight
import com.nmrc.datastructure.viewmodel.TreeViewModel


@OptIn(ExperimentalUnitApi::class)
@Composable
fun CoreBTUi(
    isDark: Boolean = isSystemInDarkTheme(),
    viewModel: TreeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {


}


