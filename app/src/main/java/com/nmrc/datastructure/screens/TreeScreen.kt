package com.nmrc.datastructure.screens


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nmrc.datastructure.components.Header
import com.nmrc.datastructure.components.tree_screen.FormTree
import com.nmrc.datastructure.components.tree_screen.domain.BinaryNodeTree
import com.nmrc.datastructure.components.tree_screen.domain.BinaryTree
import com.nmrc.datastructure.components.tree_screen.domain.BinaryTreeBalanceType
import com.nmrc.datastructure.components.tree_screen.domain.ComposableTreeStyle
import com.nmrc.datastructure.components.tree_screen.ui.CoreBTUi
import com.nmrc.datastructure.model.Medicine
import com.nmrc.datastructure.model.Patient
import com.nmrc.datastructure.ui.theme.BlueVariantAlt
import com.nmrc.datastructure.ui.theme.Gray
import com.nmrc.datastructure.viewmodel.TreeViewModel

@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun TreeScreen(
    navController: NavHostController,
    isDark: Boolean = isSystemInDarkTheme(),
    viewModel: TreeViewModel = viewModel()
) {

    val state = rememberBottomSheetScaffoldState()
    val color = if (isDark) BlueVariantAlt else Gray

    var tempMedicine by remember {
        mutableStateOf(
            Patient(
                "",
                "",
                0,
                'm',
                ""
            )
        )
    }


    // Define the tree as a mutable BinaryNodeTree object
    var tree: BinaryTree by remember { mutableStateOf(BinaryNodeTree()) }


    // Define mutable variables which impact selection and style
    var balanceType by remember {
        mutableStateOf(BinaryTreeBalanceType.UNBALANCED)
    }
    var treeStyle by remember {
        mutableStateOf(ComposableTreeStyle())
    }
    var selectedIndex by remember {
        mutableStateOf(-1)
    }
    // NOT IDEAL... FIGURE OUT WHY NOT RECOMPOSING!
    var drawPicture by remember {
        mutableStateOf(false)
    }

    // Passed into composable which draws the tree
    var nodeComposableDataList by remember {
        mutableStateOf(tree.returnComposableData())
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

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
                    title = "Arboles Binarios",
                    subtitle = "Medicamentos"
                )

                FormTree(add = { medicine, priceU ->
                    viewModel.binaryTree.value.add(
                        Medicine(
                            medicine,
                            priceU
                        )
                    )
                })



            }
        }

    }, sheetContent = {
        CoreBTUi()
    }, scaffoldState = state,
        sheetShape = RoundedCornerShape(16.dp),
        sheetBackgroundColor = color,
        drawerBackgroundColor = Color.White
    )
}