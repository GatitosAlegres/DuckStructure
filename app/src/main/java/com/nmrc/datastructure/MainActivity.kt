package com.nmrc.datastructure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nmrc.datastructure.navigation.Navigation
import com.nmrc.datastructure.ui.theme.DataStructureTheme
import com.nmrc.datastructure.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            val mainViewModel: MainViewModel = viewModel()
            DataStructureTheme {
                Navigation(mainViewModel)
            }

        }

    }
}