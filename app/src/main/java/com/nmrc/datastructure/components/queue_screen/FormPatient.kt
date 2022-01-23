package com.nmrc.datastructure.components.queue_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nmrc.datastructure.components.ActionIconBottom
import com.nmrc.datastructure.components.DropDownMenu
import com.nmrc.datastructure.ui.theme.Green
import com.nmrc.datastructure.ui.theme.Orange

@Composable
fun FormPatient(
    enqueue: (String, String, Int, Char, String) -> Unit,
    onEdit: Boolean = false,
    edit: ((String, String, Int, Char, String) -> Unit)? = null
) {
    var firstName1 by remember { mutableStateOf("") }
    var lastName1 by remember { mutableStateOf("") }
    var age1 by remember { mutableStateOf(0) }
    var gender1 by remember { mutableStateOf('m') }
    var dni by remember { mutableStateOf("") }

    OutlinedTextField(
        textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
        value = firstName1,
        onValueChange = {
            firstName1 = it
        }, label = {
            Text(text = "Nombres")
        })

    OutlinedTextField(
        textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
        value = lastName1,
        onValueChange = {
            lastName1 = it
        }, label = {
            Text(text = "Apellidos")
        })

    Row() {
        DropDownMenu(
            modifier = Modifier.fillMaxWidth(0.4f),
            list = listOf("Masculino", "Femenino"),
            label = "Sexo",
            select = {
                gender1 = it[0].lowercaseChar()
            })

        Spacer(modifier = Modifier.width(16.dp))

        DropDownMenu(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(64.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            list = (1..42).map { it.toString() },
            label = "Edad",
            select = {
                age1 = if (it.isNotEmpty())
                    it.toInt()
                else
                    0
            })
    }

    Spacer(modifier = Modifier.width(16.dp))

    OutlinedTextField(
        textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
        modifier = Modifier
            .fillMaxWidth(0.6f),
        value = dni,
        onValueChange = {
            dni = it
        }, label = {
            Text(text = "DNI")
        })


    Spacer(modifier = Modifier.height(16.dp))



    ActionIconBottom(
        icon = Icons.Outlined.Clear,
        tint = Orange,
        content = "Limpiar",
        onClick = {
            firstName1 = ""
            lastName1 = ""
            age1 = 0
            dni = ""
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    if (!onEdit) {
        ActionIconBottom(
            icon = Icons.Outlined.Done,
            tint = Green,
            content = "Agregar a la Cola",
            onClick = {
                enqueue(
                    firstName1,
                    lastName1,
                    age1,
                    gender1,
                    dni
                )
                firstName1 = ""
                lastName1 = ""
                age1 = 0
                dni = ""
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

    } else {
        ActionIconBottom(
            icon = Icons.Outlined.Done,
            tint = Green,
            content = "Editar",
            onClick = {
                if (edit != null) {
                    edit(
                        firstName1,
                        lastName1,
                        age1,
                        gender1,
                        dni
                    )
                }
                firstName1 = ""
                lastName1 = ""
                age1 = 0
                dni = ""
            }
        )
    }

}