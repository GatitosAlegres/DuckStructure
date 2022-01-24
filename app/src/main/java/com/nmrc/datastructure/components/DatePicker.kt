package com.nmrc.datastructure.components

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nmrc.datastructure.ui.theme.BlueVariant
import com.nmrc.datastructure.ui.theme.Yellow
import java.util.*

@Composable
fun DatePicker(context: Context, date: (String) -> Unit, isDark: Boolean = isSystemInDarkTheme()){

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth: Int ->
            date.value = "$dayOfMonth/${month + 1}/$year"
        }, year, month, day
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        date(date.value)
        Spacer(modifier = Modifier.size(16.dp))
        IconButton(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(if (isDark) BlueVariant else Yellow)
                .padding(4.dp),
            onClick = {
            datePickerDialog.show()
        }) {
            Row {
                Text(text = date.value)
                if (date.value.isNotEmpty())
                    Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.CalendarToday, contentDescription = "")
            }
        }
    }
}