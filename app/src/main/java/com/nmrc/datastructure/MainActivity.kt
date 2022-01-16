package com.nmrc.datastructure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nmrc.datastructure.navigation.Navigation
import com.nmrc.datastructure.test.Main
import com.nmrc.datastructure.ui.theme.DataStructureTheme

class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStructureTheme {
                // A surface container using the 'background' color from the theme
                Navigation()
            }
        }
        Main.cerna()
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DataStructureTheme {
        Navigation()
    }
}