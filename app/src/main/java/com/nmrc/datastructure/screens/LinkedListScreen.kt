package com.nmrc.datastructure.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nmrc.datastructure.components.Header
import com.nmrc.datastructure.ui.theme.BlueMaterial

@Composable
fun LinkedListScreen(navController: NavHostController) {
    Scaffold(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
            .padding(
                top = 64.dp,
                start = 16.dp,
                end = 16.dp
            )) {
            item {

                IconButton(onClick = {
                    navController.navigate(Screen.MainScreen.route)
                }) {
                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = "",
                        tint = BlueMaterial,
                        modifier = Modifier
                            .size(35.dp)
                    )
                }
                
                Header(title = "Listas Ligadas", subtitle = "")
            }
        }
        
    }
}