package com.nmrc.datastructure.components.tree_screen.domain

import com.nmrc.datastructure.components.tree_screen.domain.BinaryNodeChildType

// We will traverse a list of node data, and return a list of node composable data

data class NodeComposableData(
    val path: List<BinaryNodeChildType>, // EX: LEFT, LEFT, RIGHT, LEFT
    val value: Int, // The value the node holds
    val height: Int, // The height of the node
)
