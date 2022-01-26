package com.nmrc.datastructure.components.tree_screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.nmrc.datastructure.ui.theme.Green
import com.nmrc.datastructure.ui.theme.GreenDarkMaterial
import com.nmrc.datastructure.ui.theme.Orange

@Composable
fun FormTree(
    add: (String, Float) -> Unit,
    onEdit: Boolean = false,
    edit: ((String, Float) -> Unit)? = null,
    isDark: Boolean = isSystemInDarkTheme()
) {
    var medicine by remember { mutableStateOf("") }
    var priceU by remember { mutableStateOf(0f) }

    OutlinedTextField(
        textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
        value = medicine,
        onValueChange = {
            medicine = it
        }, label = {
            Text(text = "Nombre del Medicamento")
        })

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedTextField(
        textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
        modifier = Modifier
            .fillMaxWidth(0.3f),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        value = priceU.toString(),
        onValueChange = {
            if (it.isNotEmpty())
                priceU = it.toFloat()
        }, label = {
            Text(text = "Precio por Unidad")
        })

    Spacer(modifier = Modifier.height(16.dp))

    ActionIconBottom(
        icon = Icons.Outlined.Clear,
        tint = Orange,
        content = "Limpiar",
        onClick = {
            medicine = ""
            priceU = 0f
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    if (!onEdit) {
        ActionIconBottom(
            icon = Icons.Outlined.Done,
            tint = if (isDark) Green else GreenDarkMaterial,
            content = "Agregar al Arbol Binario",
            onClick = {
                add(
                    medicine,
                    priceU
                )
                medicine = ""
                priceU = 0f

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
                        medicine,
                        priceU
                    )
                }
                medicine = ""
                priceU = 0f
            }
        )
    }

}