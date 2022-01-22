package com.nmrc.datastructure.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nmrc.core.linkedlist.LinkedList
import com.nmrc.datastructure.model.Doctor

class LListViewModel : ViewModel() {

    val list: MutableState<LinkedList<Doctor>> = mutableStateOf(LinkedList())
    val count = mutableStateOf(0)

    fun addEnd(doctor: Doctor) {
        list.value.addEnd(doctor)
    }

    fun addStart(doctor: Doctor) {
        list.value.addStart(doctor)
    }

    fun toList(): List<Doctor> {
        val data = ArrayList<Doctor>()
       
        list.value.forEach {
            data.add(it)
        }
        return data
    }

    fun hasBeenAdded() = count.value++
}