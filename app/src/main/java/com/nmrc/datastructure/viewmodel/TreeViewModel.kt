package com.nmrc.datastructure.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.nmrc.core.tree.Tree
import com.nmrc.datastructure.components.tree_screen.domain.BinaryNodeTree
import com.nmrc.datastructure.components.tree_screen.domain.NodeComposableData
import com.nmrc.core.model.Medicine

class TreeViewModel : ViewModel() {

    private val _binaryTree: MutableState<Tree<Medicine>> = mutableStateOf(Tree { m, m2 -> (m.priceU - m2.priceU).toInt() })
    val binaryTree: State<Tree<Medicine>> = _binaryTree

    private val _tree: MutableState<BinaryNodeTree> =  mutableStateOf(BinaryNodeTree())
    val tree: State<BinaryNodeTree> = _tree

    private val _nodeComposableDataList: MutableState<List<NodeComposableData>> = mutableStateOf(tree.value.returnComposableData())
    val nodeComposableDataList: State<List<NodeComposableData>> = _nodeComposableDataList

    val count = mutableStateOf(0)

    fun add(medicine: Medicine) {
        _binaryTree.value.add(medicine)
        _tree.value.insert(medicine.priceU.toFloat())
        _nodeComposableDataList.value = _tree.value.returnComposableData()
    }

    fun setTree(node: BinaryNodeTree) {
        _tree.value = node
    }

    fun setData(data: List<NodeComposableData>?) {
        _nodeComposableDataList.value = data as List<NodeComposableData>
    }

    fun statusChange() = count.value++

}