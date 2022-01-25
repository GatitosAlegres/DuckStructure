package com.nmrc.datastructure.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nmrc.core.tree.Tree
import com.nmrc.datastructure.model.Medicine

class TreeViewModel : ViewModel() {

    val binaryTree: MutableState<Tree<Medicine>> = mutableStateOf(Tree { m, m2 -> (m.priceU - m2.priceU).toInt() })

}