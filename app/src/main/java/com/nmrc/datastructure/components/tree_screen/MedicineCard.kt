package com.nmrc.datastructure.components.tree_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmrc.core.model.Medicine
import com.nmrc.datastructure.ui.theme.*

@Composable
fun MedicineCard(
    medicine: Medicine,
    isDark: Boolean = isSystemInDarkTheme()) {
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
                        text = medicine.name[0].uppercase()?:"N",
                        style = TextStyle(
                            fontSize = 32.sp,
                            color = color)
                    )
                }
                Column() {
                    Text(text = medicine.name, color = color)
                    Text(
                        text = "S/ ${medicine.priceU}",
                        style = TextStyle(
                            fontSize = 11.sp,
                            color = color
                        )
                    )
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MedicinePrevCard() {
    Box(modifier = Modifier.fillMaxSize()) {
        MedicineCard(
            Medicine(
                "CernaMol",
                12f
            )
        )
    }
}