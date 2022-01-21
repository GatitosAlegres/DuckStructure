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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nmrc.datastructure.components.ActionIconBottom
import com.nmrc.datastructure.components.DropDownMenu
import com.nmrc.datastructure.components.Header
import com.nmrc.datastructure.model.Doctor
import com.nmrc.datastructure.ui.theme.BlueMaterial
import com.nmrc.datastructure.ui.theme.GreenDarkMaterial
import com.nmrc.datastructure.ui.theme.Purple500
import com.nmrc.datastructure.ui.theme.Purple700
import com.nmrc.datastructure.viewmodel.LListViewModel

@ExperimentalMaterialApi
@Composable
fun LinkedListScreen(
    navController: NavHostController,
    isDark: Boolean = isSystemInDarkTheme(),
    viewModel: LListViewModel = viewModel()
) {

    val state = rememberBottomSheetScaffoldState()
    val color = if (isDark) Color(0xFF455A64) else Purple500

    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf(0) }
    var gender by remember { mutableStateOf('m') }
    var yearsOfService by remember { mutableStateOf(0) }
    var specialty by remember { mutableStateOf("") }

    val alumnos = viewModel.list.value

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
                        tint = BlueMaterial,
                        modifier = Modifier
                            .size(35.dp)
                    )
                }

                Header(title = "Listas Ligadas", subtitle = "Doctores")



                OutlinedTextField(
                    value = nombre,
                    onValueChange = {
                        nombre = it
                    }, label = {
                        Text(text = "Nombres")
                    })

                OutlinedTextField(
                    value = apellidos,
                    onValueChange = {
                        apellidos = it
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
                            edad = if (it.isNotEmpty())
                                it.toInt()
                            else 0
                        })
                }

                Row() {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(0.4f),
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
                            .fillMaxWidth(0.5f),
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
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {
                    ActionIconBottom(
                        icon = Icons.Outlined.Clear,
                        tint = Purple700,
                        content = "Limpiar",
                        onClick = {
                            nombre = ""
                            apellidos = ""
                            edad = 0
                            gender = 'm'
                            yearsOfService = 0
                            specialty = ""
                        }
                    )

                    ActionIconBottom(
                        icon = Icons.Outlined.Done,
                        tint = GreenDarkMaterial,
                        content = "Agregar",
                        onClick = {
                            viewModel.add(
                                Doctor(
                                    nombre,
                                    apellidos,
                                    edad,
                                    gender,
                                    yearsOfService,
                                    specialty
                                )
                            )
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
                    subtitle = "Seleccione el caso de uso",
                    color = MaterialTheme.colors.onPrimary
                )

                Header(
                    title = "Filtrar",
                    subtitle = "Seleccione el caso de uso",
                    color = MaterialTheme.colors.onPrimary
                )

                Header(
                    title = "Ordenar",
                    subtitle = "Seleccione el caso de uso",
                    color = MaterialTheme.colors.onPrimary
                )

                Header(
                    title = "Modificar Campos",
                    subtitle = "Seleccione el caso de uso",
                    color = MaterialTheme.colors.onPrimary
                )

                Header(
                    title = "Reducir",
                    subtitle = "Seleccione el caso de uso",
                    color = MaterialTheme.colors.onPrimary
                )

                Header(
                    title = "RESULTADO",
                    subtitle = "",
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = "Total : ${viewModel.count.value}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    textAlign = TextAlign.End
                )

                Text(text = viewModel.list.value.toString())
            }
        }


    }, scaffoldState = state,
        sheetShape = RoundedCornerShape(16.dp),
        sheetBackgroundColor = color
    )
}