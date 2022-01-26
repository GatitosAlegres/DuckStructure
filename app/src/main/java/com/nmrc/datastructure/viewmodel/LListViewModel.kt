package com.nmrc.datastructure.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nmrc.core.linkedlist.LinkedList
import com.nmrc.core.model.Doctor

class LListViewModel : ViewModel() {

    private val _list: MutableState<LinkedList<Doctor>> = mutableStateOf(LinkedList())
    val list: State<LinkedList<Doctor>> = _list
    val count = mutableStateOf(0)

    fun statusChange() = count.value++
}