package com.nmrc.datastructure.components.tree_screen.domain

abstract class BinaryTree {
    open var size: Int = 0
        protected set

    abstract fun insert(value: Float)
    abstract fun remove(value: Float)
    abstract fun contains(value: Float): Boolean
    abstract fun returnComposableData(): List<NodeComposableData>
    abstract fun asBalancedTree(): BinaryNodeTree
    abstract fun heapify(isMin: Boolean): HeapTree
}