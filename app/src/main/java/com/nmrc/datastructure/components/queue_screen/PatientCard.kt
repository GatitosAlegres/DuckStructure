package com.nmrc.datastructure.components.queue_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Female
import androidx.compose.material.icons.outlined.Male
import androidx.compose.material.icons.outlined.Pin
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmrc.datastructure.components.ActionIconBottom
import com.nmrc.datastructure.ui.theme.*

@Composable
fun PatientCard(
    firstName: String,
    lastName: String,
    age: Int,
    gender: Char,
    dni: String,
    isDark: Boolean = isSystemInDarkTheme()
) {

    val bgColor = if (isDark) BlueVariantDark else Gray
    val color = if(isDark) White else Dark

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, if (!isDark) GrayDark else Color.Transparent),
        backgroundColor = bgColor,
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
                        text = lastName[0].uppercase(),
                        style = TextStyle(
                            fontSize = 32.sp,
                            color = color)
                    )
                }
                Column {
                    Text(text = firstName, color = color)
                    Text(
                        text = lastName,
                        style = TextStyle(
                            fontSize = 11.sp,
                            color = color
                        )
                    )
                }

            }
            Row(Modifier.fillMaxWidth(), Arrangement.End) {
                ActionIconBottom(
                    icon = if(gender=='m') Icons.Outlined.Male else Icons.Outlined.Female,
                    tint = if (gender=='m') Orange else Pink,
                    content = if(gender=='m') "Masculino" else "Femenino",
                    onClick = {})

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(Modifier.fillMaxWidth(0.5f)) {
                    Text(
                        text = "Datos Personales",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            color = if (isDark) Yellow else GreenDarkMaterial,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Edad: $age", color = color)
                    Text(text = "DNI: $dni", color = color)
                }
            }
        }
    }
}