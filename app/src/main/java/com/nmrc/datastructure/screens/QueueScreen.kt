package com.nmrc.datastructure.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.SkipNext
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nmrc.datastructure.components.ActionIconBottom
import com.nmrc.datastructure.components.DropDownMenu
import com.nmrc.datastructure.components.Header
import com.nmrc.datastructure.components.queue_screen.FormPatient
import com.nmrc.datastructure.components.queue_screen.PatientCard
import com.nmrc.datastructure.components.queue_screen.PatientDataStream
import com.nmrc.datastructure.components.queue_screen.PatientSortDataStream
import com.nmrc.datastructure.model.Patient
import com.nmrc.datastructure.ui.theme.*
import com.nmrc.datastructure.util.toList
import com.nmrc.datastructure.viewmodel.MainViewModel

@ExperimentalMaterialApi
@Composable
fun QueueScreen(
    navController: NavHostController,
    isDark: Boolean = isSystemInDarkTheme(),
    mainViewModel: MainViewModel
) {

    val state = rememberBottomSheetScaffoldState()
    val color = if (isDark) BlueVariantAlt else Gray

    var tempPatient by remember {
        mutableStateOf(
            Patient(
                "",
                "",
                0,
                'm',
                ""
            )
        )
    }

    BottomSheetScaffold(modifier = Modifier.fillMaxSize(), content = {
        LazyColumn(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 64.dp
                )
        ) {
            item {
                Spacer(modifier = Modifier.height(64.dp))
                IconButton(onClick = {
                    navController.navigate(Screen.MainScreen.route)
                }) {
                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = "",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .size(35.dp)
                    )
                }

                Header(
                    title = "Colas",
                    subtitle = "Pacientes"
                )

                FormPatient(enqueue = { firstName, lastName, age, gender, dni ->
                    mainViewModel.list.value.enqueue(
                        Patient(
                            firstName,
                            lastName,
                            age,
                            gender,
                            dni
                        )
                    )
                    mainViewModel.statusChange()
                })

            }
        }

    }, sheetContent = {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .width(8.dp)
                            .height(8.dp)
                            .clip(CircleShape)
                            .padding(0.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                if (!mainViewModel.list.value.isEmpty) {
                    Header(
                        title = "Contar",
                        subtitle = "Seleccione el caso de uso"
                    )

                    PatientDataStream(
                        label = "Contar por",
                        onAge = { restrict, age ->

                            Spacer(modifier = Modifier.height(8.dp))

                            ActionIconBottom(
                                icon = Icons.Rounded.Person,
                                tint = Orange,
                                content =
                                if (restrict) {
                                    mainViewModel.list.value.count { it.age <= age }.toString()
                                } else {
                                    mainViewModel.list.value.count { it.age >= age }.toString()
                                }
                            ) {}
                        },
                        onGender = { gender ->
                            Spacer(modifier = Modifier.height(8.dp))

                            ActionIconBottom(
                                icon = Icons.Rounded.Person,
                                tint = Orange,
                                content =
                                mainViewModel.list.value.count { it.gender == gender }
                                    .toString()
                            ) {}
                        }
                    )

                    Header(
                        title = "Filtrar",
                        subtitle = "Seleccione el caso de uso"
                    )

                    PatientDataStream(
                        label = "Filtrar por",
                        onAge = { restrict, age ->

                            Spacer(modifier = Modifier.height(8.dp))

                            if (restrict) {
                                mainViewModel.list.value.filter {
                                    it.age <= age
                                }.toList()
                                    .forEach { patient ->
                                        PatientCard(
                                            firstName = patient.firstName,
                                            lastName = patient.lastName,
                                            age = patient.age,
                                            gender = patient.gender,
                                            dni = patient.dni
                                        )
                                    }
                            } else {
                                mainViewModel.list.value.filter {
                                    it.age >= age
                                }.toList()
                                    .forEach { patient ->
                                        PatientCard(
                                            firstName = patient.firstName,
                                            lastName = patient.lastName,
                                            age = patient.age,
                                            gender = patient.gender,
                                            dni = patient.dni
                                        )
                                    }
                            }
                        },
                        onGender = { gender ->
                            Spacer(modifier = Modifier.height(8.dp))

                            mainViewModel.list.value.filter {
                                it.gender == gender
                            }.toList()
                                .forEach { patient ->
                                    PatientCard(
                                        firstName = patient.firstName,
                                        lastName = patient.lastName,
                                        age = patient.age,
                                        gender = patient.gender,
                                        dni = patient.dni
                                    )
                                }
                        }
                    )

                    Header(
                        title = "Ordenar",
                        subtitle = "Seleccione el caso de uso"
                    )

                    PatientSortDataStream(
                        label = "Ordenar por",
                        onLastName = { asc ->
                            if (asc) {

                                val copy = mainViewModel.list.value.map { it }

                                copy.sort { pat1, pat2 ->
                                    pat1.lastName.compareTo(pat2.lastName)
                                }

                                copy.toList().forEach { patient ->
                                    PatientCard(
                                        firstName = patient.firstName,
                                        lastName = patient.lastName,
                                        age = patient.age,
                                        gender = patient.gender,
                                        dni = patient.dni
                                    )
                                }
                            } else {

                                val copy = mainViewModel.list.value.map { it }

                                copy.sort { pat1, pat2 ->
                                    pat2.lastName.compareTo(pat1.lastName)
                                }
                                copy.toList().forEach { patient ->
                                    PatientCard(
                                        firstName = patient.firstName,
                                        lastName = patient.lastName,
                                        age = patient.age,
                                        gender = patient.gender,
                                        dni = patient.dni
                                    )
                                }
                            }
                        },
                        onAge = { asc ->
                            if (asc) {

                                val copy = mainViewModel.list.value.map { it }

                                copy.sort { pat1, pat2 ->
                                    pat1.age - pat2.age
                                }

                                copy.toList().forEach { patient ->
                                    PatientCard(
                                        firstName = patient.firstName,
                                        lastName = patient.lastName,
                                        age = patient.age,
                                        gender = patient.gender,
                                        dni = patient.dni
                                    )
                                }
                            } else {

                                val copy = mainViewModel.list.value.map { it }

                                copy.sort { pat1, pat2 ->
                                    pat2.age - pat1.age
                                }
                                copy.toList().forEach { patient ->
                                    PatientCard(
                                        firstName = patient.firstName,
                                        lastName = patient.lastName,
                                        age = patient.age,
                                        gender = patient.gender,
                                        dni = patient.dni
                                    )
                                }
                            }
                        },
                        onGender = { gender ->
                            if (gender == 'm') {

                                val copy = mainViewModel.list.value.map { it }

                                copy.sort { pat1, pat2 ->
                                    pat2.gender.compareTo(pat1.gender)
                                }

                                copy.toList().forEach { patient ->
                                    PatientCard(
                                        firstName = patient.firstName,
                                        lastName = patient.lastName,
                                        age = patient.age,
                                        gender = patient.gender,
                                        dni = patient.dni
                                    )
                                }
                            } else {

                                val copy = mainViewModel.list.value.map { it }

                                copy.sort { pat1, pat2 ->
                                    pat1.gender.compareTo(pat2.gender)
                                }
                                copy.toList().forEach { patient ->
                                    PatientCard(
                                        firstName = patient.firstName,
                                        lastName = patient.lastName,
                                        age = patient.age,
                                        gender = patient.gender,
                                        dni = patient.dni
                                    )
                                }
                            }
                        }
                    )

                    Header(
                        title = "Modificar Campos",
                        subtitle = "Seleccione el paciente a modificar"
                    )

                    DropDownMenu(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(64.dp),
                        list = mainViewModel.list.value.toList().map { it.lastName },
                        label = "Pacientes",
                        select = { lastName ->
                            mainViewModel.list.value.forEach { patient ->
                                if (patient.lastName.equals(lastName)) {
                                    tempPatient = patient
                                    return@forEach
                                }
                            }
                        })

                    if (tempPatient.lastName.isNotEmpty()) {
                        FormPatient(
                            enqueue = { _, _, _, _, _ -> },
                            onEdit = true,
                            edit = { firstName, lastName, age, gender, dni ->
                                mainViewModel.list.value.insert(
                                    mainViewModel.list.value.indexOf(tempPatient),
                                    Patient(
                                        firstName,
                                        lastName,
                                        age,
                                        gender,
                                        dni
                                    )
                                )
                                mainViewModel.list.value.remove(tempPatient)
                                mainViewModel.statusChange()
                                tempPatient.lastName = ""
                            })
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Header(
                        title = "COLA DE PACIENTES",
                        subtitle = ""
                    )
                    ActionIconBottom(
                        Modifier
                            .fillMaxWidth()
                            .align(CenterHorizontally),
                        icon = Icons.Outlined.SkipNext,
                        tint = if (isDark) Green else GreenDarkMaterial,
                        content = "Desencolar"
                    ) {

                        mainViewModel.list.value.dequeue()
                        mainViewModel.statusChange()
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    LinearProgressIndicator(
                        progress = mainViewModel.list.value.length() / 100f,
                        color = if (isDark) Green else GreenDarkMaterial,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp)
                            .clip(CircleShape)
                    )
                }else {
                    Header(
                        title = "",
                        subtitle = "Pruebe agregando un nuevo Paciente")
                }
                Text(
                    text = "Total : ${mainViewModel.count.value}",
                    color = Color.Transparent,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.End
                )
                mainViewModel.list.value.toList().forEach { patient ->
                    PatientCard(
                        firstName = patient.firstName,
                        lastName = patient.lastName,
                        age = patient.age,
                        gender = patient.gender,
                        dni = patient.dni
                    )
                }
            }
        }
    }, scaffoldState = state,
        sheetShape = RoundedCornerShape(16.dp),
        sheetBackgroundColor = color
    )
}