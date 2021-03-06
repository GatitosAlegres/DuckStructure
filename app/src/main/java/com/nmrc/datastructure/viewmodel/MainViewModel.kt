package com.nmrc.datastructure.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nmrc.core.queue.Queue
import com.nmrc.core.stack.Stack
import com.nmrc.core.model.Appointment
import com.nmrc.core.model.Patient

class MainViewModel : ViewModel() {

    private val _list: MutableState<Queue<Patient>> = mutableStateOf(Queue())
    val list: State<Queue<Patient>> = _list

    private val _listAppointment: MutableState<Stack<Appointment>> = mutableStateOf(Stack())
    val listAppointment: State<Stack<Appointment>> = _listAppointment

    val count = mutableStateOf(0)

    fun statusChange() = count.value++

}