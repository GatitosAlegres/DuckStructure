package com.nmrc.datastructure.components.stack_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmrc.datastructure.components.Header
import com.nmrc.datastructure.model.Appointment


@Composable
fun AppointmentCard(
    appointment: Appointment
) {

    val patient = appointment.patient
    val detail = appointment.detail

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .padding(8.dp)
            ) {

                Column(Modifier.fillMaxWidth(0.25f)) {
                    Text(
                        text = patient.lastName[0].uppercase(),
                        style = TextStyle(
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(Modifier.fillMaxWidth(0.75f)) {
                    Text(
                        text = patient.firstName,
                        style = TextStyle(
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = patient.lastName,
                        style = TextStyle(
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Row {
                        Text(
                            text = "Edad: ${patient.age}",
                            style = TextStyle(
                                color = MaterialTheme.colors.onBackground
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (patient.gender == 'm') "Sexo: M" else "Sexo: F",
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Dni: ${patient.dni}",
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }

            Divider(modifier = Modifier.padding(8.dp))

            Header(title = "", subtitle = "Detalles")

            Row {
                Text(text = "Razon: ${detail.reason}",
                    style = TextStyle(
                        color = MaterialTheme.colors.onBackground
                    ))
                Text(text = "Fecha: ${detail.date}",
                    style = TextStyle(
                        color = MaterialTheme.colors.onBackground
                    ))
            }
            
            Text(text = "Sintomas",
                style = TextStyle(
                    color = MaterialTheme.colors.onBackground
                ))

            detail.symptom.forEach{
                Text(text = it,
                    style = TextStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontSize = 16.sp
                    ))
            }
        }
    }
}