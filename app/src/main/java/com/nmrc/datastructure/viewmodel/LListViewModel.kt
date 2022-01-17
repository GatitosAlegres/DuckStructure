package com.nmrc.datastructure.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.nmrc.core.linkedlist.LinkedList
import com.nmrc.datastructure.model.Alumno

class LListViewModel : ViewModel() {

    val list: MutableState<LinkedList<Alumno>>  = mutableStateOf(LinkedList())
    val count = mutableStateOf(0)

    fun add(alumno: Alumno) {
        list.value.addEnd(alumno)
    }

    fun hasBeenAdded() = count.value++
}