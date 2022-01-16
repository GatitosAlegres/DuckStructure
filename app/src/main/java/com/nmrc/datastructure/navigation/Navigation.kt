package com.nmrc.datastructure.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nmrc.datastructure.screens.*

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {

        composable(
            route = Screen.MainScreen.route
        )
        {

            MainScreen(
                navController = navController
            )
        }

        composable(route = Screen.LinkedListScreen.route) {
            LinkedListScreen(navController = navController)
        }

        composable(route = Screen.QueueScreen.route) {
            QueueScreen(navController = navController)
        }

        composable(route = Screen.StackScreen.route) {
            StackScreen(navController = navController)
        }

        composable(route = Screen.TreeScreen.route) {
            TreeScreen(navController = navController)
        }
    }
}