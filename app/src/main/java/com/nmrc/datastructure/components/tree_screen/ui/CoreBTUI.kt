package com.nmrc.datastructure.components.tree_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.nmrc.datastructure.components.tree_screen.domain.*
import com.nmrc.datastructure.ui.theme.BlueVariantAlt
import com.nmrc.datastructure.ui.theme.GrayLight


@OptIn(ExperimentalUnitApi::class)
@Composable
fun CoreBTUi(
    isDark: Boolean = isSystemInDarkTheme()
) {
    // Define the tree as a mutable BinaryNodeTree object
    var tree: BinaryTree by remember { mutableStateOf(BinaryNodeTree()) }


    // Define mutable variables which impact selection and style
    var balanceType by remember {
        mutableStateOf(BinaryTreeBalanceType.UNBALANCED)
    }
    var treeStyle by remember {
        mutableStateOf(ComposableTreeStyle())
    }
    var selectedIndex by remember {
        mutableStateOf(-1)
    }
    // NOT IDEAL... FIGURE OUT WHY NOT RECOMPOSING!
    var drawPicture by remember {
        mutableStateOf(false)
    }

    // Passed into composable which draws the tree
    var nodeComposableDataList by remember {
        mutableStateOf(tree.returnComposableData())
    }
    val scaffoldState = rememberScaffoldState()


    tree.insert(value = 5)
    nodeComposableDataList = tree.returnComposableData()
    tree.insert(value = 16)
    nodeComposableDataList = tree.returnComposableData()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(if (isDark) BlueVariantAlt else GrayLight),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(if (isDark) BlueVariantAlt else GrayLight),
                    contentAlignment = Alignment.Center
                ) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .width(8.dp)
                            .height(8.dp)
                            .clip(CircleShape)
                            .padding(0.dp)
                    )
                }
            }
        },
        bottomBar = {
            if (nodeComposableDataList.isNotEmpty()) {
                ComposableBottomBar(
                    selected = selectedIndex,
                    onReset = {
                        selectedIndex = -1

                        val special =
                            (balanceType == BinaryTreeBalanceType.AVL_TREE || balanceType == BinaryTreeBalanceType.MIN_HEAP)
                        tree = if (tree is BinaryNodeTree) BinaryNodeTree(special) else HeapTree(
                            special
                        )
                        val tmpTheme = treeStyle.theme
                        treeStyle = ComposableTreeStyle()
                        treeStyle.theme = tmpTheme
                        nodeComposableDataList = tree.returnComposableData()
                    },
                    onRemove = {

                        tree.remove(selectedIndex)
                        selectedIndex = -1
                        nodeComposableDataList = tree.returnComposableData()
                    }
                )
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = { scaffoldState.snackbarHostState }
    ) {
        // Contains either the tree or a placeholder message
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isDark) BlueVariantAlt else GrayLight)
                .padding(
                    bottom =
                    if (nodeComposableDataList.isNotEmpty())
                        55.dp
                    else
                        0.dp
                ) // Add padding if bottom bar is present
        ) {
            val boxWithConstraintsScope = this

            // If the tree is not empty, draw the tree
            if (nodeComposableDataList.isNotEmpty()) {
                ComposableTree(
                    data = nodeComposableDataList,
                    style = treeStyle,
                    drawPicture = drawPicture, // NOT IDEAL... FIGURE OUT WHY NOT RECOMPOSING!
                    onNodeSelect = {

                        selectedIndex = it ?: -1
                    }
                )
            } else {
                // If empty, display placeholder message
                ComposableEmptyTree(boxWithConstraintsScope)
            }

            // Snackbar used to display error messages
            ComposableSnackbar(
                snackbarHostState = scaffoldState.snackbarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp),
            )
        }
    }

}


