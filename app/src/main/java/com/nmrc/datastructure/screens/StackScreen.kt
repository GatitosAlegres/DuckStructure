package com.nmrc.datastructure.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nmrc.datastructure.components.ActionIconBottom
import com.nmrc.datastructure.components.DropDownMenu
import com.nmrc.datastructure.components.Header
import com.nmrc.datastructure.components.stack_screen.AppointmentCard
import com.nmrc.datastructure.components.stack_screen.FormAppointmentD
import com.nmrc.datastructure.model.Appointment
import com.nmrc.datastructure.model.AppointmentDetail
import com.nmrc.datastructure.model.Patient
import com.nmrc.datastructure.ui.theme.BlueVariant
import com.nmrc.datastructure.ui.theme.Gray
import com.nmrc.datastructure.ui.theme.Green
import com.nmrc.datastructure.ui.theme.GreenDarkMaterial
import com.nmrc.datastructure.util.toList
import com.nmrc.datastructure.viewmodel.MainViewModel

@ExperimentalMaterialApi
@Composable
fun StackScreen(
    navController: NavHostController,
    isDark: Boolean = isSystemInDarkTheme(),
    mainViewModel: MainViewModel
) {

    val state = rememberBottomSheetScaffoldState()
    val color = if (isDark) BlueVariant else Gray

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
                    title = "Pilas",
                    subtitle = "Reservas"
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

                Header(
                    title = "",
                    subtitle = "Detalles de la Reserva"
                )



                FormAppointmentD(saveAppointment = { date, reason, fever, headache, cough ->

                    val symptom = ArrayList<String>()

                    if (fever)
                        symptom.add("Fiebre")
                    if (headache)
                        symptom.add("Dolor de Cabeza")
                    if (cough)
                        symptom.add("Tos")

                    mainViewModel.listAppointment.value.push(
                        Appointment(
                            tempPatient, AppointmentDetail(
                                date,
                                reason,
                                symptom
                            )
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

                Text(
                    text = "Total : ${mainViewModel.count.value}",
                    color = Color.Transparent,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.End
                )

                if (!mainViewModel.listAppointment.value.isEmpty) {
                    AppointmentCard(
                        appointment = mainViewModel.listAppointment.value.peek()
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        ActionIconBottom(
                            icon = Icons.Outlined.Done,
                            tint = if (isDark) Green else GreenDarkMaterial,
                            content = "Anterior",
                            onClick = {


                            }
                        )

                        ActionIconBottom(
                            icon = Icons.Outlined.Done,
                            tint = if (isDark) Green else GreenDarkMaterial,
                            content = "Siguiente",
                            onClick = {

                                mainViewModel.listAppointment.value.pop()
                                mainViewModel.statusChange()
                            }
                        )
                    }
                }
            }
        }
    }, scaffoldState = state,
        sheetShape = RoundedCornerShape(16.dp),
        sheetBackgroundColor = color,
        drawerBackgroundColor = Color.White
    )
}