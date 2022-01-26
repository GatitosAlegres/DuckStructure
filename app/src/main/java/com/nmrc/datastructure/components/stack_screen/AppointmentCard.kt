package com.nmrc.datastructure.components.stack_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.Masks
import androidx.compose.material.icons.filled.Sick
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmrc.datastructure.components.ActionIconBottom
import com.nmrc.core.model.Appointment
import com.nmrc.core.model.AppointmentDetail
import com.nmrc.core.model.Patient
import com.nmrc.datastructure.ui.theme.*


@Composable
fun AppointmentCard(
    appointment: Appointment,
    isDark: Boolean = isSystemInDarkTheme()
) {

    val patient = appointment.patient
    val detail = appointment.detail

    val bgColor = if (isDark) BlueVariantDark else Gray
    val color = if(isDark) White else Dark

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = bgColor,
        border = BorderStroke(1.dp, if (!isDark) GrayDark else Color.Transparent),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.fillMaxWidth(0.1f)) {
                    Text(
                        text = patient.lastName[0].uppercase(),
                        style = TextStyle(
                            fontSize = 32.sp,
                            color = color)
                    )
                }
                Column {
                    Text(text = patient.firstName, color = color)
                    Text(
                        text = patient.lastName,
                        style = TextStyle(
                            fontSize = 11.sp,
                            color = color
                        )
                    )
                }

            }
            Row(Modifier.fillMaxWidth(), Arrangement.End) {
                detail.symptom.forEach {
                    ActionIconBottom(
                        icon = when (it) {
                            "fiebre" -> Icons.Default.Sick
                            "dcabeza" -> Icons.Default.Headset
                            "tos" -> Icons.Default.Masks
                            else -> Icons.Default.Sick
                        },
                        tint = if (isDark) Orange else YellowDark,
                        content = it,
                        onClick = {})
                    Spacer(modifier = Modifier.width(8.dp))
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(Modifier.fillMaxWidth(0.3f)) {
                    Text(
                        text = "Datos Personales",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            color = if (isDark) Yellow else GreenDarkMaterial,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Edad: ${patient.age}", color = color)
                    Text(text = "Sexo: ${patient.gender.uppercase()}", color = color)
                    Text(text = "DNI: ${patient.dni}", color = color)
                }

                Column(Modifier.fillMaxWidth(0.5f)) {
                    Text(
                        text = "Detalles de la Reserva",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            color = if (isDark) Yellow else GreenDarkMaterial,
                        ),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Fecha: ${detail.date}", color = color)
                    Text(text = "Razon: ${detail.reason}", color = color)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppointment() {
    Box(modifier = Modifier.fillMaxSize()) {
        AppointmentCard(
            Appointment(
                Patient(
                    "George",
                    "Peraldo Namoc",
                    20,
                    'm',
                    "70461710"
                ),
                AppointmentDetail("24/01/2022", "Mimir", listOf("fiebre", "tos"))
            )
        )
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewDarkAppointment() {
    Box(modifier = Modifier.fillMaxSize()) {
        AppointmentCard(
            Appointment(
                Patient(
                    "Jhon Tercero",
                    "Cerna Alvarado",
                    23,
                    'm',
                    "00000000"
                ),
                AppointmentDetail("24/01/2022", "Sida", listOf("fiebre", "tos", "dcabeza"))
            )
        )
    }
}