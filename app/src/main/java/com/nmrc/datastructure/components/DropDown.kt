package com.nmrc.datastructure.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.toSize
import com.nmrc.datastructure.ui.theme.BlueLight
import com.nmrc.datastructure.ui.theme.BlueVariantDark
import com.nmrc.datastructure.ui.theme.WhiteMaterial

@Composable
fun DropDownMenu(
                 modifier: Modifier = Modifier,
                 list: List<String>,
                 label: String,
                 keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                 select: (String) -> Unit,
                 isDark: Boolean = isSystemInDarkTheme()) {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = list
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val bgColor = if(isDark) BlueVariantDark else WhiteMaterial

    Column(modifier = modifier) {
        OutlinedTextField(
            textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
            value = selectedText,
            onValueChange = { selectedText = it },
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(label) },
            trailingIcon = {
                Icon(icon, "",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                .background(bgColor)
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    select(selectedText)
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }

}