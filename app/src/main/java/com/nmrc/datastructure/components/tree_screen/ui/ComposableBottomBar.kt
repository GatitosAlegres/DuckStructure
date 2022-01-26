package com.nmrc.datastructure.components.tree_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.RemoveCircle
import androidx.compose.material.icons.rounded.RestartAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmrc.datastructure.components.ActionIconBottom
import com.nmrc.datastructure.ui.theme.*

@ExperimentalUnitApi
@Composable
fun ComposableBottomBar(
    selected: Float,
    onReset: () -> Unit,
    onRemove: () -> Unit,
    isDark: Boolean = isSystemInDarkTheme()
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(3.dp)
            .background(if (isDark) BlueVariantAltBg else GrayMaterial)
            .border(1.dp, Color.Transparent, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        val maxWidth = this.maxWidth
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .border(1.dp, Color.Transparent, shape = RoundedCornerShape(16.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {

                ActionIconBottom(
                    icon = Icons.Rounded.RestartAlt,
                    tint = Orange,
                    content = "Restaurar") {
                    onReset()
                }


                Text(
                    text = when {
                        maxWidth > 480.dp -> if (selected != -1f) "$selected Selecionado" else "Seleccione un Medicamento"
                        else -> if (selected != -1f) " " else "Toque para Seleccionar"
                    },
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    letterSpacing = 0.sp,
                    color = if(isDark) WhiteMaterial else BlueVariantDark,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 4.dp, end = 10.dp)
                )
                if (selected != -1f) {
                    ActionIconBottom(
                        icon = Icons.Rounded.RemoveCircle,
                        tint = Red,
                        content = "Eliminar") {
                        onRemove()
                    }
                }

        }
    }
}