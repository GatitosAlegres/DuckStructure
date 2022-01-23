package com.nmrc.datastructure.components.queue_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nmrc.datastructure.components.DropDownMenu

@Composable
fun PatientDataStream(
    label: String,
    onAge: @Composable (restrict: Boolean, value: Int) -> Unit,
    onGender: @Composable (Char) -> Unit
) {

    var count by remember {
        mutableStateOf("")
    }
    var gender by remember {
        mutableStateOf('m')
    }
    var ageRestrict by remember {
        mutableStateOf(false)
    }
    var age by remember {
        mutableStateOf(0)
    }

    DropDownMenu(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(64.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        list = listOf("Sexo", "Edad"),
        label = label,
        select = {
            count = if (it.isNotEmpty())
                it
            else ""
        })

    if (count.isNotEmpty()) {
        when (count) {
            "Sexo" -> {
                DropDownMenu(
                    modifier = Modifier.fillMaxWidth(0.4f),
                    list = listOf("Masculino", "Femenino"),
                    label = "Sexo",
                    select = {
                        gender = it[0].lowercaseChar()
                    })
                onGender(gender)
            }
            "Edad" -> {
                DropDownMenu(
                    modifier = Modifier.fillMaxWidth(0.4f),
                    list = listOf("Mayor o igual que", "Menor o igual que"),
                    label = "Restriccion",
                    select = {
                        // Send data value to viewmodel
                        if (it.isNotEmpty())
                            when (it) {
                                "Mayor o igual que" -> ageRestrict = false
                                "Menor o igual que" -> ageRestrict = true
                            }
                    })

                DropDownMenu(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(64.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    list = (16..42).map { it.toString() },
                    label = "Valor",
                    select = {
                        // Send data value to viewmodel
                        if (it.isNotEmpty())
                            age = it.toInt()
                    })
                onAge(ageRestrict, age)
            }
        }
    }
}