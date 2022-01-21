package com.nmrc.datastructure.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DoctorCard(
        firstName: String,
        lastName: String,
        age: Int,
        gender: Char,
        yearsOfService: Int,
        specialty: String) {
    Card(modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                    .padding(8.dp)) {
            Icon(
                Icons.Outlined.Person,
                modifier = Modifier.size(32.dp).fillMaxWidth(),
                contentDescription = "")

            Spacer(modifier = Modifier.width(16.dp))

            Column() {
                Text(text = firstName)
                Text(text = lastName)
                Row {
                    Text(text = age.toString())
                    Text(text = if (gender == 'm') "Masculino" else "Femenino")
                }
                Row {
                    Text(text = yearsOfService.toString())
                    Text(text = specialty)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DoctorCard(
            firstName = "George",
            lastName = "Peraldo Namoc",
            age = 20,
            gender = 'm',
            yearsOfService = 2,
            specialty = "Dormir"
        )
    }
}