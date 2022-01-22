package com.nmrc.datastructure.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nmrc.core.linkedlist.LinkedList
import com.nmrc.datastructure.model.Doctor
import java.util.function.Predicate

class LListViewModel : ViewModel() {

    val list: MutableState<LinkedList<Doctor>> = mutableStateOf(LinkedList())
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

    fun addEnd(doctor: Doctor) {
        list.value.addEnd(doctor)
    }

    fun addStart(doctor: Doctor) {
        list.value.addStart(doctor)
    }


    fun count(predicate: Predicate<Doctor>): Int {
        return list.value.count(predicate)
    }

    fun filter(predicate: Predicate<Doctor>) =
        toList(list.value.filter(predicate) as LinkedList<Doctor>)

    fun hasBeenAdded() = count.value++
}