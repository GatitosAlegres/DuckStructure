package com.nmrc.datastructure.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nmrc.datastructure.components.ActionIconBottom
import com.nmrc.datastructure.components.Header
import com.nmrc.datastructure.components.tree_screen.FormTree
import com.nmrc.datastructure.components.tree_screen.MedicineCard
import com.nmrc.datastructure.components.tree_screen.domain.*
import com.nmrc.datastructure.components.tree_screen.ui.*
import com.nmrc.core.model.Medicine
import com.nmrc.datastructure.ui.theme.*
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
    var order by remember {
        mutableStateOf(-1)
    }

    var balanceType by remember {
        mutableStateOf(BinaryTreeBalanceType.UNBALANCED)
    }
    var treeStyle by remember {
        mutableStateOf(ComposableTreeStyle())
    }
    var selectedIndex by remember {
        mutableStateOf(-1f)
    }

    var drawPicture by remember {
        mutableStateOf(false)
    }

    var query by remember { mutableStateOf("") }

    var response by remember {
        mutableStateOf(Medicine("",0f))
    }

    val scaffoldState = rememberScaffoldState()

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
                    viewModel.add(
                        Medicine(
                            medicine,
                            priceU
                        )
                    )
                    viewModel.statusChange()
                })

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
                    value = query,
                    onValueChange = {
                        query = it
                    }, label = {
                        Text(text = "Nombre del medicamento")
                    })

                
                
                Spacer(modifier = Modifier.height(8.dp))

                ActionIconBottom(icon = Icons.Default.Search, tint = Yellow, content = "Buscar") {
                    response = viewModel.binaryTree.value.binarySearch(Medicine(query,0f)).element


                }

                if(!response.name.isNullOrEmpty()) {
                    MedicineCard(medicine = response)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    ActionIconBottom(icon = Icons.Default.Preview, tint = Blue, content = "PreOrden") {
                        order=0
                        viewModel.binaryTree.value.preOrder(viewModel.binaryTree.value.root)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    ActionIconBottom(icon = Icons.Default.Preview, tint = Blue, content = "InOrden") {
                        order=1
                        viewModel.binaryTree.value.inOrder(viewModel.binaryTree.value.root)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    ActionIconBottom(icon = Icons.Default.Preview, tint = Blue, content = "PosOrden") {
                        order=2
                        viewModel.binaryTree.value.postOrder(viewModel.binaryTree.value.root)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if(order!=-1) {
                    viewModel.binaryTree.value.enumerated.forEach{ medicine ->
                        MedicineCard(medicine = medicine)
                    }
                    viewModel.binaryTree.value.enumerated.clear()
                }
            }
        }

    }, sheetContent = {

        Scaffold(
            topBar = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(if (isDark) BlueVariantAlt else GrayLight),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .background(if (isDark) BlueVariantAlt else GrayLight),
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
            },
            bottomBar = {
                if (viewModel.nodeComposableDataList.value.isNotEmpty()) {
                    ComposableBottomBar(
                        selected = selectedIndex,
                        onReset = {
                            selectedIndex = -1f

                            val special =
                                (balanceType == BinaryTreeBalanceType.AVL_TREE || balanceType == BinaryTreeBalanceType.MIN_HEAP)
                            viewModel.setTree( if (viewModel.tree.value is BinaryNodeTree) BinaryNodeTree(special) else HeapTree(
                                special
                            ) as BinaryNodeTree)
                            val tmpTheme = treeStyle.theme
                            treeStyle = ComposableTreeStyle()
                            treeStyle.theme = tmpTheme
                            viewModel.setData(emptyList())
                        },
                        onRemove = {

                            viewModel.tree.value.remove(selectedIndex)
                            selectedIndex = -1f
                            viewModel.setData(viewModel.tree.value.returnComposableData())

                        }
                    )
                }
            },
            scaffoldState = scaffoldState,
            snackbarHost = { scaffoldState.snackbarHostState }
        ) {
            Text(
                text = "Total : ${viewModel.count.value}",
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.End
            )
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (isDark) BlueVariantAlt else GrayLight)
                    .padding(
                        bottom =
                        if (viewModel.nodeComposableDataList.value.isNotEmpty())
                            55.dp
                        else
                            0.dp
                    )
            ) {
                val boxWithConstraintsScope = this

                if (viewModel.nodeComposableDataList.value.isNotEmpty()) {
                    ComposableTree(
                        data = viewModel.nodeComposableDataList.value,
                        style = treeStyle,
                        drawPicture = drawPicture,
                        onNodeSelect = {

                            selectedIndex = (it ?: -1f) as Float
                        }
                    )

                } else {
                    ComposableEmptyTree(boxWithConstraintsScope)
                }

                ComposableSnackbar(
                    snackbarHostState = scaffoldState.snackbarHostState,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 5.dp),
                )
            }
        }
    }, scaffoldState = state,
        sheetShape = RoundedCornerShape(16.dp),
        sheetBackgroundColor = color,
        drawerBackgroundColor = Color.White
    )
}