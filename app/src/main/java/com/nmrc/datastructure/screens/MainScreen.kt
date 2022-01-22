package com.nmrc.datastructure.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nmrc.datastructure.R
import com.nmrc.datastructure.components.CardX
import com.nmrc.datastructure.components.Header
import com.nmrc.datastructure.ui.theme.BlueMaterial
import com.nmrc.datastructure.ui.theme.BlueVariant
import com.nmrc.datastructure.ui.theme.GrayMaterial
import com.nmrc.datastructure.ui.theme.White

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    navController: NavHostController
) {

    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),

        content = {

            LazyColumn(
                modifier = Modifier
                    .padding(
                        top = 64.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
            ) {

                item {
                    IconButton(onClick = {
                        expanded = !expanded
                    }) {
                        Icon(
                            Icons.Outlined.Menu,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onBackground,
                            modifier = Modifier
                                .size(35.dp)
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }) {
                        DropdownMenuItem(onClick = {}) {
                            Text(text = "Ajustes")
                        }
                        DropdownMenuItem(onClick = {

                        }) {
                            Text(text = "Acerca de")
                        }
                    }

                    Header(
                        title = "Estructura de Datos Orientado a Objetos",
                        subtitle = "Una coleccion de estructura de datos para experimentar en tiempo real."
                    )

                    LazyRow(
                        content = {
                            item {

                                Divider(modifier = Modifier.padding(8.dp))

                                CardX(
                                    title = "Listas Enlazadas",
                                    painter = painterResource(id = R.drawable.list),
                                    description = "Pruebe creando clases arbitrarias y almacenandolas en Listas Enlazadas por Nodos",
                                    onDetail = {}) {
                                    navController.navigate(Screen.LinkedListScreen.route)
                                }

                                Divider(modifier = Modifier.padding(16.dp))

                                CardX(
                                    title = "Colas",
                                    painter = painterResource(id = R.drawable.list),
                                    description = "Pruebe creando clases arbitrarias y almacenandolas en Listas Enlazadas por Nodos",
                                    onDetail = {}) {
                                    navController.navigate(Screen.QueueScreen.route)
                                }

                                Divider(modifier = Modifier.padding(16.dp))

                                CardX(
                                    title = "Pilas",
                                    painter = painterResource(id = R.drawable.list),
                                    description = "Pruebe creando clases arbitrarias y almacenandolas en Listas Enlazadas por Nodos",
                                    onDetail = {}) {
                                    navController.navigate(Screen.StackScreen.route)
                                }

                                Divider(modifier = Modifier.padding(16.dp))

                                CardX(
                                    title = "Arboles Binarios",
                                    painter = painterResource(id = R.drawable.list),
                                    description = "Pruebe creando clases arbitrarias y almacenandolas en Listas Enlazadas por Nodos",
                                    onDetail = {}) {
                                    navController.navigate(Screen.TreeScreen.route)
                                }

                                Divider(modifier = Modifier.padding(16.dp))

                            }
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp, bottom = 60.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column() {
                            Text(
                                text = "Universidad Nacional de Trujillo",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = GrayMaterial,
                                    fontFamily = FontFamily.SansSerif
                                )
                            )
                            Text(
                                text = "Valle Jequetepeque",
                                style = TextStyle(
                                    fontWeight = FontWeight.Light,
                                    color = GrayMaterial,
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 10.sp
                                )
                            )
                        }
                        Image(
                            painter = painterResource(
                                id = R.drawable.unt_logo
                            ),
                            contentDescription = "",
                            Modifier.size(80.dp),
                            alpha = 0.4f
                        )
                    }
                }
            }
        })
}