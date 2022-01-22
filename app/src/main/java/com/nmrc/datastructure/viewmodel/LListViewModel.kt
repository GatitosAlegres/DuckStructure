package com.nmrc.datastructure.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nmrc.core.linkedlist.LinkedList
import com.nmrc.datastructure.model.Doctor

class LListViewModel : ViewModel() {

    private val _list: MutableState<LinkedList<Doctor>> = mutableStateOf(LinkedList())
    val list: State<LinkedList<Doctor>> = _list
    val count = mutableStateOf(0)

    companion object {

        fun toList(list: LinkedList<Doctor>): List<Doctor> {
            val data = ArrayList<Doctor>()

            list.forEach {
                data.add(it)
            }
            return data
        }
    }

    fun hasBeenAdded() = count.value++
}