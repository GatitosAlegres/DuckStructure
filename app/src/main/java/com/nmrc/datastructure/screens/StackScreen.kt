package com.nmrc.datastructure.screens

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nmrc.datastructure.components.Header
import com.nmrc.datastructure.ui.theme.BlueVariant
import com.nmrc.datastructure.ui.theme.Gray
import com.nmrc.datastructure.viewmodel.MainViewModel

@ExperimentalMaterialApi
@Composable
fun StackScreen(
    navController: NavHostController,
    isDark: Boolean = isSystemInDarkTheme(),
    mainViewModel: MainViewModel
) {

    val state = rememberBottomSheetScaffoldState()
    val color = if (isDark) BlueVariant else Gray

    BottomSheetScaffold(modifier = Modifier.fillMaxSize(), content = {
        LazyColumn(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 64.dp
                )
        ) {
            item {
                Spacer(modifier = Modifier.height(64.dp))
                IconButton(onClick = {
                    navController.navigate(Screen.MainScreen.route)
                }) {
                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = "",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .size(35.dp)
                    )
                }

                Header(
                    title = "Pilas",
                    subtitle = "Reservas"
                )

                Log.d("PRUEBAS","STACK SCREEN : ${mainViewModel.list.value}")
                Log.d("PRUEBAS","STACK SCREEN SIZE: ${mainViewModel.list.value.length()}")
            }
        }

    }, sheetContent = {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .width(8.dp)
                            .height(8.dp)
                            .clip(CircleShape)
                            .padding(0.dp)
                    )
                }












            }
        }
    },  scaffoldState = state,
        sheetShape = RoundedCornerShape(16.dp),
        sheetBackgroundColor = color,
        drawerBackgroundColor = Color.White
    )
}