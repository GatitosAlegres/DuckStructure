package com.nmrc.datastructure.screens

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nmrc.datastructure.components.*
import com.nmrc.datastructure.components.linkedlist_screen.DataStream
import com.nmrc.datastructure.components.linkedlist_screen.DoctorCard
import com.nmrc.datastructure.components.linkedlist_screen.FormDoc
import com.nmrc.datastructure.components.linkedlist_screen.SortDataStream
import com.nmrc.datastructure.model.Doctor
import com.nmrc.datastructure.ui.theme.*
import com.nmrc.datastructure.util.toList
import com.nmrc.datastructure.viewmodel.LListViewModel

@ExperimentalMaterialApi
@Composable
fun LinkedListScreen(
    navController: NavHostController,
    isDark: Boolean = isSystemInDarkTheme(),
    viewModel: LListViewModel = viewModel()
) {

    val state = rememberBottomSheetScaffoldState()
    val color = if (isDark) BlueVariant else Gray

    var tempDoc by remember {
        mutableStateOf(
            Doctor(
                "",
                "",
                0,
                'm',
                0,
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
                    title = "Listas Enlazadas",
                    subtitle = "Doctores"
                )

                FormDoc(addEnd = { firstName, lastName, age, gender, yearsOfService, specialty ->
                    viewModel.list.value.addEnd(
                        Doctor(
                            firstName,
                            lastName,
                            age,
                            gender,
                            yearsOfService,
                            specialty
                        )
                    )
                    viewModel.hasBeenAdded()

                }, addStart = { firstName, lastName, age, gender, yearsOfService, specialty ->
                    viewModel.list.value.addStart(
                        Doctor(
                            firstName,
                            lastName,
                            age,
                            gender,
                            yearsOfService,
                            specialty
                        )
                    )
                    viewModel.hasBeenAdded()
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
                    label = "Contar por",
                    onAge = { restrict, value ->

                        Spacer(modifier = Modifier.height(8.dp))

                        ActionIconBottom(
                            icon = Icons.Rounded.Person,
                            tint = Orange,
                            content =
                            if (restrict) {
                                viewModel.list.value.count { it.age <= value }.toString()
                            } else {
                                viewModel.list.value.count { it.age >= value }.toString()
                            }
                        ) {}


                    }, onYearsServices = { yearsOfServices ->
                        Spacer(modifier = Modifier.height(8.dp))

                        ActionIconBottom(
                            icon = Icons.Rounded.Person,
                            tint = Orange,
                            content =
                            viewModel.list.value.count { it.yearsOfService == yearsOfServices }
                                .toString()
                        ) {}
                    }, onGender = { gender ->
                        Spacer(modifier = Modifier.height(8.dp))

                        ActionIconBottom(
                            icon = Icons.Rounded.Person,
                            tint = Orange,
                            content = viewModel.list.value.count {
                                it.gender == gender
                            }.toString()
                        ) {}
                    }, onSpecialty = { specialty ->
                        Spacer(modifier = Modifier.height(8.dp))

                        ActionIconBottom(
                            icon = Icons.Rounded.Person,
                            tint = Orange,
                            content =
                            viewModel.list.value.count { it.specialty == specialty }.toString()
                        ) {}
                    }
                )

                Header(
                    title = "Filtrar",
                    subtitle = "Seleccione el caso de uso"
                )

                DataStream(
                    label = "Filtrar por",
                    onAge = { restrict, value ->

                        if (restrict)
                            viewModel.list.value.filter {
                                it.age <= value
                            }.toList()
                                .forEach { doctor ->
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
                            viewModel.list.value.filter {
                                it.age >= value
                            }.toList()
                                .forEach { doctor ->
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
                        viewModel.list.value.filter {
                            it.yearsOfService == yearsOfServices
                        }.toList()
                            .forEach { doctor ->
                                DoctorCard(
                                    firstName = doctor.firstName,
                                    lastName = doctor.lastName,
                                    age = doctor.age,
                                    gender = doctor.gender,
                                    yearsOfService = doctor.yearsOfService,
                                    specialty = doctor.specialty
                                )
                            }
                    }, onGender = { gender ->
                        viewModel.list.value.filter {
                            it.gender == gender
                        }.toList()
                            .forEach { doctor ->
                                DoctorCard(
                                    firstName = doctor.firstName,
                                    lastName = doctor.lastName,
                                    age = doctor.age,
                                    gender = doctor.gender,
                                    yearsOfService = doctor.yearsOfService,
                                    specialty = doctor.specialty
                                )
                            }
                    },
                    onSpecialty = { specialty ->
                        viewModel.list.value.filter {
                            it.specialty == specialty
                        }.toList()
                            .forEach { doctor ->
                                DoctorCard(
                                    firstName = doctor.firstName,
                                    lastName = doctor.lastName,
                                    age = doctor.age,
                                    gender = doctor.gender,
                                    yearsOfService = doctor.yearsOfService,
                                    specialty = doctor.specialty
                                )
                            }
                    })


                Header(
                    title = "Ordenar",
                    subtitle = "Seleccione el caso de uso"
                )

                SortDataStream(
                    label = "Ordenar por",
                    onLastName = { asc ->
                        if (asc) {
                            viewModel.list.value.sort { doc1, doc2 ->
                                doc1.lastName.compareTo(doc2.lastName)
                            }

                            viewModel.list.value
                                .toList().forEach { doctor ->
                                    DoctorCard(
                                        firstName = doctor.firstName,
                                        lastName = doctor.lastName,
                                        age = doctor.age,
                                        gender = doctor.gender,
                                        yearsOfService = doctor.yearsOfService,
                                        specialty = doctor.specialty
                                    )
                                }
                        } else {
                            viewModel.list.value.sort { doc1, doc2 ->
                                doc2.lastName.compareTo(doc1.lastName)
                            }
                            viewModel.list.value
                                .toList().forEach { doctor ->
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
                    },
                    onAge = { asc ->
                        if (asc) {
                            viewModel.list.value.sort { doc1, doc2 ->
                                doc1.age - doc2.age
                            }

                            viewModel.list.value
                                .toList()
                                .forEach { doctor ->
                                    DoctorCard(
                                        firstName = doctor.firstName,
                                        lastName = doctor.lastName,
                                        age = doctor.age,
                                        gender = doctor.gender,
                                        yearsOfService = doctor.yearsOfService,
                                        specialty = doctor.specialty
                                    )
                                }
                        } else {
                            viewModel.list.value.sort { doc1, doc2 ->
                                doc2.age - doc1.age
                            }

                            viewModel.list.value
                                .toList()
                                .forEach { doctor ->
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

                    },
                    onYearsServices = { asc ->
                        if (asc) {
                            viewModel.list.value.sort { doc1, doc2 ->
                                doc1.yearsOfService - doc2.yearsOfService
                            }

                            viewModel.list.value
                                .toList()
                                .forEach { doctor ->
                                    DoctorCard(
                                        firstName = doctor.firstName,
                                        lastName = doctor.lastName,
                                        age = doctor.age,
                                        gender = doctor.gender,
                                        yearsOfService = doctor.yearsOfService,
                                        specialty = doctor.specialty
                                    )
                                }
                        } else {
                            viewModel.list.value.sort { doc1, doc2 ->
                                doc2.yearsOfService - doc1.yearsOfService
                            }
                            viewModel.list.value
                                .toList()
                                .forEach { doctor ->
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
                    },
                    onGender = { gender ->
                        if (gender == 'm') {
                            viewModel.list.value.sort { doc1, doc2 ->
                                doc2.gender.compareTo(doc1.gender)
                            }

                            viewModel.list.value
                                .toList()
                                .forEach { doctor ->
                                    DoctorCard(
                                        firstName = doctor.firstName,
                                        lastName = doctor.lastName,
                                        age = doctor.age,
                                        gender = doctor.gender,
                                        yearsOfService = doctor.yearsOfService,
                                        specialty = doctor.specialty
                                    )
                                }
                        } else {
                            viewModel.list.value.sort { doc1, doc2 ->
                                doc1.gender.compareTo(doc2.gender)
                            }

                            viewModel.list.value
                                .toList()
                                .forEach { doctor ->
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
                    },
                    onSpecialty = { asc ->
                        if (asc) {
                            viewModel.list.value.sort { doc1, doc2 ->
                                doc1.specialty.compareTo(doc2.specialty)
                            }

                            viewModel.list.value
                                .toList()
                                .forEach { doctor ->
                                    DoctorCard(
                                        firstName = doctor.firstName,
                                        lastName = doctor.lastName,
                                        age = doctor.age,
                                        gender = doctor.gender,
                                        yearsOfService = doctor.yearsOfService,
                                        specialty = doctor.specialty
                                    )
                                }
                        } else {
                            viewModel.list.value.sort { doc1, doc2 ->
                                doc2.specialty.compareTo(doc1.specialty)
                            }

                            viewModel.list.value
                                .toList()
                                .forEach { doctor ->
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
                    })

                Header(
                    title = "Modificar Campos",
                    subtitle = "Seleccione el doctor a modificar"
                )


                DropDownMenu(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(64.dp),
                    list = viewModel.list.value.toList().map { it.lastName },
                    label = "Doctores",
                    select = { lastName ->
                        viewModel.list.value.forEach { doctor ->
                            if (doctor.lastName.equals(lastName)) {
                                tempDoc = doctor
                                return@forEach
                            }
                        }
                    })

                if (tempDoc.lastName.isNotEmpty()) {
                    FormDoc(addEnd = { _, _, _, _, _, _ -> },
                        addStart = { _, _, _, _, _, _ -> },
                        onEdit = true,
                        edit = { firstName, lastName, age, gender, yearsOfService, speciality ->
                            viewModel.list.value.add(
                                viewModel.list.value.indexOf(tempDoc),
                                Doctor(
                                    firstName,
                                    lastName,
                                    age,
                                    gender,
                                    yearsOfService,
                                    speciality
                                )
                            )
                            viewModel.list.value.remove(tempDoc)
                            viewModel.hasBeenAdded()
                            tempDoc.lastName = ""
                        })
                }

                Header(
                    title = "Reducir",
                    subtitle = "Seleccione el caso de uso"
                )

                Header(
                    title = "TOTAL DE DOCTORES",
                    subtitle = ""
                )
                Text(
                    text = "Total : ${viewModel.count.value}",
                    color = Color.Transparent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    textAlign = TextAlign.End
                )

                viewModel.list.value.toList().forEach { doctor ->
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