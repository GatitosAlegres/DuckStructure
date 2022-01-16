package com.nmrc.datastructure.screens

sealed class Screen(val route: String) {

    object MainScreen : Screen("main_screen")

    object LinkedListScreen : Screen("linkedList_screen")

    object QueueScreen : Screen("queue_screen")

    object StackScreen : Screen("stack_screen")

    object TreeScreen : Screen("tree_screen")
}
