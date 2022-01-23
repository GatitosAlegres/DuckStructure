package com.nmrc.datastructure.components.linkedlist_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nmrc.datastructure.components.ActionIconBottom
import com.nmrc.datastructure.components.DropDownMenu
import com.nmrc.datastructure.ui.theme.Green
import com.nmrc.datastructure.ui.theme.Orange


@Composable
fun FormDoc(
    addEnd: (String, String, Int, Char, Int, String) -> Unit,
    addStart: (String, String, Int, Char, Int, String) -> Unit,
    onEdit: Boolean = false,
    edit: ((String, String, Int, Char, Int, String) -> Unit)? = null
) {
    var firstName1 by remember { mutableStateOf("") }
    var lastName1 by remember { mutableStateOf("") }
    var gender1 by remember { mutableStateOf('m') }
    var age1 by remember { mutableStateOf(0) }
    var yearsOfService1 by remember { mutableStateOf(0) }
    var specialty1 by remember { mutableStateOf("") }

    OutlinedTextField(
        value = firstName1,
        onValueChange = {
            firstName1 = it
        }, label = {
            Text(text = "Nombres")
        })

    OutlinedTextField(
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
            list = (16..42).map { it.toString() },
            label = "Edad",
            select = {
                age1 = if (it.isNotEmpty())
                    it.toInt()
                else
                    0
            })
    }

    Row() {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.3f),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            value = yearsOfService1.toString(),
            onValueChange = {
                yearsOfService1 = if (it.isNotEmpty())
                    it.toInt()
                else 0
            }, label = {
                Text(text = "Años de Servicio")
            })

        Spacer(modifier = Modifier.width(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.6f),
            value = specialty1,
            onValueChange = {
                specialty1 = it
            }, label = {
                Text(text = "Especialidad")
            })
    }

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {

        ActionIconBottom(
            icon = Icons.Outlined.Clear,
            tint = Orange,
            content = "Limpiar",
            onClick = {
                firstName1 = ""
                lastName1 = ""
                age1 = 0
                yearsOfService1 = 0
                specialty1 = ""
            }
        )

        if (!onEdit) {
            ActionIconBottom(
                icon = Icons.Outlined.Done,
                tint = Green,
                content = "Agregar (Final)",
                onClick = {
                    addEnd(
                        firstName1,
                        lastName1,
                        age1,
                        gender1,
                        yearsOfService1,
                        specialty1
                    )
                    firstName1 = ""
                    lastName1 = ""
                    age1 = 0
                    yearsOfService1 = 0
                    specialty1 = ""
                }
            )

            ActionIconBottom(
                icon = Icons.Outlined.Done,
                tint = Green,
                content = "Agregar (Inicio)",
                onClick = {
                    addStart(
                        firstName1,
                        lastName1,
                        age1,
                        gender1,
                        yearsOfService1,
                        specialty1
                    )
                    firstName1 = ""
                    lastName1 = ""
                    age1 = 0
                    yearsOfService1 = 0
                    specialty1 = ""
                }
            )
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
                            yearsOfService1,
                            specialty1
                        )
                    }
                    firstName1 = ""
                    lastName1 = ""
                    age1 = 0
                    yearsOfService1 = 0
                    specialty1 = ""

                }
            )
        }
    }
}
