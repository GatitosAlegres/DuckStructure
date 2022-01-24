package com.nmrc.datastructure.components.stack_screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.nmrc.datastructure.components.ActionIconBottom
import com.nmrc.datastructure.components.Chip
import com.nmrc.datastructure.components.DatePicker
import com.nmrc.datastructure.components.Header
import com.nmrc.datastructure.ui.theme.Green
import com.nmrc.datastructure.ui.theme.GreenDarkMaterial
import com.nmrc.datastructure.ui.theme.Orange

@Composable
fun FormAppointmentD(
    saveAppointment: (String, String, Boolean, Boolean, Boolean) -> Unit,
    onEdit: Boolean = false,
    edit: ((String, String, Boolean, Boolean, Boolean) -> Unit)? = null,
    isDark: Boolean = isSystemInDarkTheme()
) {
    var date by remember { mutableStateOf("") }
    var reason by remember { mutableStateOf("") }

    var fever by remember {
        mutableStateOf(false)
    }

    var headache by remember {
        mutableStateOf(false)
    }

    var cough by remember {
        mutableStateOf(false)
    }

    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.4f),
            textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
            value = reason,
            onValueChange = {
                reason = it
            }, label = {
                Text(text = "Razon")
            })

        Spacer(modifier = Modifier.width(16.dp))

        DatePicker(context = LocalContext.current, date = { date = it })
    }

    Header(
        title = "",
        subtitle = "Sintomas"
    )

    LazyRow(Modifier.padding(bottom = 8.dp)) {
        item() {

            Chip(
                name = "fiebre",
                content = "Fiebre",
                onSelectionChanged = { _, isSelect ->
                    fever = isSelect
                })

            Spacer(modifier = Modifier.width(8.dp))

            Chip(
                name = "dcabeza",
                content = "Dolor de cabeza",
                onSelectionChanged = { _, isSelect ->
                    headache = isSelect
                })

            Spacer(modifier = Modifier.width(8.dp))

            Chip(
                name = "tos",
                content = "Tos",
                onSelectionChanged = { _, isSelect ->
                    cough = isSelect
                })

        }
    }

    ActionIconBottom(
        icon = Icons.Outlined.Clear,
        tint = Orange,
        content = "Limpiar",
        onClick = {
            date = ""
            reason = ""


        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    if (!onEdit) {
        ActionIconBottom(
            icon = Icons.Outlined.Done,
            tint = if (isDark) Green else GreenDarkMaterial,
            content = "Reservar",
            onClick = {
                saveAppointment(
                    date,
                    reason,
                    fever,
                    headache,
                    cough
                )
                date = ""
                reason = ""

            }
        )

        Spacer(modifier = Modifier.height(8.dp))

    } else {
        ActionIconBottom(
            icon = Icons.Outlined.Done,
            tint = if (isDark) Green else GreenDarkMaterial,
            content = "Editar",
            onClick = {
                if (edit != null) {
                    edit(
                        date,
                        reason,
                        fever,
                        headache,
                        cough

                    )
                }
                date = ""
                reason = ""
            }
        )
    }

}