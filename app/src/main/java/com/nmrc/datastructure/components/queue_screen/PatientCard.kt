package com.nmrc.datastructure.components.queue_screen

import androidx.compose.foundation.isSystemInDarkTheme
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
import com.nmrc.datastructure.ui.theme.Dark
import com.nmrc.datastructure.ui.theme.Gray

@Composable
fun PatientCard(
    firstName: String,
    lastName: String,
    age: Int,
    gender: Char,
    dni: String,
    isDark: Boolean = isSystemInDarkTheme()
) {

    val bgColor = if (isDark) Dark else Gray

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
                        text = lastName[0].uppercase(),
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
                        text = firstName,
                        style = TextStyle(
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = lastName,
                        style = TextStyle(
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Row {
                        Text(
                            text = "Edad: $age",
                            style = TextStyle(
                                color = MaterialTheme.colors.onBackground
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (gender == 'm') "Sexo: M" else "Sexo: F",
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Dni: $dni",
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            Divider(modifier = Modifier.padding(8.dp))
        }
    }
}