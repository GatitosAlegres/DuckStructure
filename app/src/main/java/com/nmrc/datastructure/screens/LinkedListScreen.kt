package com.nmrc.datastructure.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nmrc.core.linkedlist.LinkedList
import com.nmrc.datastructure.components.ActionIconBottom
import com.nmrc.datastructure.components.DoctorCard
import com.nmrc.datastructure.components.DropDownMenu
import com.nmrc.datastructure.components.Header
import com.nmrc.datastructure.model.Doctor
import com.nmrc.datastructure.ui.theme.BlueLight
import com.nmrc.datastructure.ui.theme.BlueVariant
import com.nmrc.datastructure.ui.theme.Green
import com.nmrc.datastructure.ui.theme.Orange
import com.nmrc.datastructure.viewmodel.LListViewModel

@ExperimentalMaterialApi
@Composable
fun LinkedListScreen(
    navController: NavHostController,
    isDark: Boolean = isSystemInDarkTheme(),
    viewModel: LListViewModel = viewModel()
) {

    val state = rememberBottomSheetScaffoldState()
    val color = if (isDark) BlueVariant else BlueLight

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf(0) }
    var gender by remember { mutableStateOf('m') }
    var yearsOfService by remember { mutableStateOf(0) }
    var specialty by remember { mutableStateOf("") }

    var count by remember { mutableStateOf(" ") }
    var filter by remember { mutableStateOf(" ") }

    BottomSheetScaffold(modifier = Modifier.fillMaxSize(), content = {
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = 64.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 64.dp
                )
        ) {
            item {

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
                    title = "Listas Enlazadas",
                    subtitle = "Doctores"
                )



                OutlinedTextField(
                    value = firstName,
                    onValueChange = {
                        firstName = it
                    }, label = {
                        Text(text = "Nombres")
                    })

                OutlinedTextField(
                    value = lastName,
                    onValueChange = {
                        lastName = it
                    }, label = {
                        Text(text = "Apellidos")
                    })

                Row() {
                    DropDownMenu(
                        modifier = Modifier.fillMaxWidth(0.4f),
                        list = listOf("Masculino", "Femenino"),
                        label = "Sexo",
                        select = {
                            gender = it[0]
                        })

                    Spacer(modifier = Modifier.width(16.dp))

                    DropDownMenu(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(64.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        list = (16..42).map { it.toString() },
                        label = "Edad",
                        select = {
                            age = if (it.isNotEmpty())
                                it.toInt()
                            else 0
                        })
                }

                Row() {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(0.3f),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        value = yearsOfService.toString(),
                        onValueChange = {
                            yearsOfService = if (it.isNotEmpty())
                                it.toInt()
                            else 0
                        }, label = {
                            Text(text = "Años de Servicio")
                        })

                    Spacer(modifier = Modifier.width(16.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(0.6f),
                        value = specialty.toString(),
                        onValueChange = {
                            specialty = it
                        }, label = {
                            Text(text = "Especialidad")
                        })
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    ActionIconBottom(
                        icon = Icons.Outlined.Clear,
                        tint = Orange,
                        content = "Limpiar",
                        onClick = {
                            firstName = ""
                            lastName = ""
                            age = 0
                            gender = 'm'
                            yearsOfService = 0
                            specialty = ""
                        }
                    )

                    ActionIconBottom(
                        icon = Icons.Outlined.Done,
                        tint = Green,
                        content = "Agregar (Final)",
                        onClick = {
                            viewModel.addEnd(
                                Doctor(
                                    firstName,
                                    lastName,
                                    age,
                                    gender.lowercaseChar(),
                                    yearsOfService,
                                    specialty
                                )
                            )
                            firstName = ""
                            lastName = ""
                            age = 0
                            gender = 'm'
                            yearsOfService = 0
                            specialty = ""
                            viewModel.hasBeenAdded()
                        }
                    )

                    ActionIconBottom(
                        icon = Icons.Outlined.Done,
                        tint = Green,
                        content = "Agregar (Inicio)",
                        onClick = {
                            viewModel.addStart(
                                Doctor(
                                    firstName,
                                    lastName,
                                    age,
                                    gender.lowercaseChar(),
                                    yearsOfService,
                                    specialty
                                )
                            )
                            firstName = ""
                            lastName = ""
                            age = 0
                            gender = 'm'
                            yearsOfService = 0
                            specialty = ""
                            viewModel.hasBeenAdded()
                        }
                    )
                }
            }
        }

    }, sheetContent = {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            item() {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    contentAlignment = Center
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

                Header(
                    title = "Contar",
                    subtitle = "Seleccione el caso de uso"

                )


                DataStream(
                    onAge = { restrict, value ->

                        Spacer(modifier = Modifier.height(8.dp))

                        ActionIconBottom(
                            icon = Icons.Rounded.Person,
                            tint = Orange,
                            content =
                            if (restrict) {
                                viewModel.count { it.age <= value }.toString()
                            } else {
                                viewModel.count { it.age >= value }.toString()
                            }
                        ) {}


                    }, onYearsServices = { yearsOfServices ->
                        Spacer(modifier = Modifier.height(8.dp))

                        ActionIconBottom(
                            icon = Icons.Rounded.Person,
                            tint = Orange,
                            content =
                            viewModel.count { it.yearsOfService == yearsOfServices }.toString()
                        ) {}
                    }, onGender = { gender ->
                        Spacer(modifier = Modifier.height(8.dp))

                        ActionIconBottom(
                            icon = Icons.Rounded.Person,
                            tint = Orange,
                            content =
                            viewModel.count { it.gender == gender }.toString()
                        ) {}
                    }, onSpecialty = { specialty ->
                        Spacer(modifier = Modifier.height(8.dp))

                        ActionIconBottom(
                            icon = Icons.Rounded.Person,
                            tint = Orange,
                            content =
                            viewModel.count { it.specialty == specialty }.toString()
                        ) {}
                    }
                )

                Header(
                    title = "Filtrar",
                    subtitle = "Seleccione el caso de uso"
                )

                DataStream(onAge = { restrict, value ->
                    Spacer(modifier = Modifier.height(8.dp))

                    if (restrict)
                        LListViewModel.toList(
                            viewModel.list.value.filter {
                                it.age <= value
                            } as LinkedList<Doctor>
                        ).forEach { doctor ->
                            DoctorCard(
                                firstName = doctor.firstName,
                                lastName = doctor.lastName,
                                age = doctor.age,
                                gender = doctor.gender,
                                yearsOfService = doctor.yearsOfService,
                                specialty = doctor.specialty
                            )
                        }
                    else
                        LListViewModel.toList(
                            viewModel.list.value.filter {
                                it.age >= value
                            } as LinkedList<Doctor>
                        ).forEach { doctor ->
                            DoctorCard(
                                firstName = doctor.firstName,
                                lastName = doctor.lastName,
                                age = doctor.age,
                                gender = doctor.gender,
                                yearsOfService = doctor.yearsOfService,
                                specialty = doctor.specialty
                            )
                        }
                }, onYearsServices = { yearsOfServices ->

                }, onGender = { gender ->

                },
                    onSpecialty = { specialty ->

                    })


                Header(
                    title = "Ordenar",
                    subtitle = "Seleccione el caso de uso"
                )

                Header(
                    title = "Modificar Campos",
                    subtitle = "Seleccione el caso de uso"
                )


                Header(
                    title = "Reducir",
                    subtitle = "Seleccione el caso de uso"
                )

                Header(
                    title = "RESULTADO",
                    subtitle = ""
                )
                Text(
                    text = "Total : ${viewModel.count.value}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    textAlign = TextAlign.End
                )

                LListViewModel.toList(viewModel.list.value).forEach { doctor ->
                    DoctorCard(
                        firstName = doctor.firstName,
                        lastName = doctor.lastName,
                        age = doctor.age,
                        gender = doctor.gender,
                        yearsOfService = doctor.yearsOfService,
                        specialty = doctor.specialty
                    )
                }
            }
        }

    }, scaffoldState = state,
        sheetShape = RoundedCornerShape(16.dp),
        sheetBackgroundColor = color,
        drawerBackgroundColor = Color.White
    )
}

@Composable
private fun DataStream(
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
        label = "Contar por",
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
                        gender = it.lowercase()[0]
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

