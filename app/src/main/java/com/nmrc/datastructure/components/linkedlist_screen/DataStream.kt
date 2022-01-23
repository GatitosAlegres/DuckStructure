package com.nmrc.datastructure.components.linkedlist_screen

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nmrc.datastructure.components.DropDownMenu

@Composable
fun DataStream(
    label: String,
    onGender: @Composable (Char) -> Unit,
    onAge: @Composable (restrict: Boolean, value: Int) -> Unit,
    onYearsServices: @Composable (Int) -> Unit,
    onSpecialty: @Composable (String) -> Unit
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
    var yearsServices by remember {
        mutableStateOf(0)
    }
    var specialty by remember {
        mutableStateOf("")
    }

    DropDownMenu(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(64.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        list = listOf("Sexo", "Edad", "Años de Servicio", "Especialidad"),
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
            "Años de Servicio" -> {
                DropDownMenu(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(64.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    list = (1..20).map { it.toString() },
                    label = "Valor",
                    select = {
                        // Send data value to viewmodel
                        if (it.isNotEmpty())
                            yearsServices = it.toInt()

                    })
                onYearsServices(yearsServices)
            }
            "Especialidad" -> {
                OutlinedTextField(
                    textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
                    modifier = Modifier
                        .fillMaxWidth(0.6f),
                    value = specialty,
                    onValueChange = {
                        if (it.isNotEmpty())
                            specialty = it
                    }, label = {
                        Text(text = "Valor")
                    })
                onSpecialty(specialty)
            }
        }
    }
}