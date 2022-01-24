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
fun PatientSortDataStream(
    label: String,
    onLastName : @Composable (asc: Boolean) -> Unit,
    onGender: @Composable (Char) -> Unit,
    onAge: @Composable (asc: Boolean) -> Unit
) {

    var count by remember {
        mutableStateOf("")
    }
    var gender by remember {
        mutableStateOf('m')
    }

    var lastName by remember {
        mutableStateOf(false)
    }

    var age by remember {
        mutableStateOf(false)
    }

    DropDownMenu(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(64.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        list = listOf("Apellidos","Sexo", "Edad"),
        label = label,
        select = {
            count = if (it.isNotEmpty())
                it
            else ""
        })

    if (count.isNotEmpty()) {
        when (count) {
            "Apellidos" -> {
                DropDownMenu(
                    modifier = Modifier.fillMaxWidth(0.4f),
                    list = listOf("Ascendente", "Descendente"),
                    label = "Orden",
                    select = {
                        // Send data value to viewmodel
                        if (it.isNotEmpty())
                            when (it) {
                                "Ascendente" -> lastName = true
                                "Descendente" -> lastName = false
                            }
                    })

                onLastName(lastName)
            }
            "Sexo" -> {
                DropDownMenu(
                    modifier = Modifier.fillMaxWidth(0.4f),
                    list = listOf("Masculino", "Femenino"),
                    label = "Sexo",
                    select = {
                        if (it.isNotEmpty())
                            gender = it.lowercase()[0]
                    })
                onGender(gender)
            }
            "Edad" -> {
                DropDownMenu(
                    modifier = Modifier.fillMaxWidth(0.4f),
                    list = listOf("Ascendente", "Descendente"),
                    label = "Orden",
                    select = {
                        // Send data value to viewmodel
                        if (it.isNotEmpty())
                            when (it) {
                                "Ascendente" -> age = true
                                "Descendente" -> age = false
                            }
                    })
                onAge(age)
            }
        }
    }
}