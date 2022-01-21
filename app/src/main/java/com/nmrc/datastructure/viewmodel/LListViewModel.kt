package com.nmrc.datastructure.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.nmrc.core.linkedlist.LinkedList
import com.nmrc.datastructure.model.Alumno
import com.nmrc.datastructure.model.Doctor

class LListViewModel : ViewModel() {

    val list: MutableState<LinkedList<Doctor>>  = mutableStateOf(LinkedList())
    val count = mutableStateOf(0)

    fun addEnd(doctor: Doctor) {
        list.value.addEnd(doctor)
    }

    fun addStart(doctor: Doctor) {
        list.value.addStart(doctor)
    }

    fun toList(): List<Doctor>  {
        val data = ArrayList<Doctor>()
        return if(list.value.size!=0) {
            list.value.forEach {
                data.add(it)
            }
            data
        }else emptyList()

    }

    fun hasBeenAdded() = count.value++
}