package com.jeanbarrossilva.realism.ui.component

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import com.jeanbarrossilva.realism.ui.default.RealismTheme

@Composable
fun RealismScaffold(isFabVisible: Boolean, onFabClick: () -> Unit = { }, content: @Composable () -> Unit) {
    val fabOpacity = if (isFabVisible) 1f else 0f

    RealismTheme.Wrap {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = if (isFabVisible) onFabClick else ({ }),
                    Modifier.drawLayer(alpha = fabOpacity),
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    Icon(Icons.Rounded.Settings)
                }
            }
        ) {
            content()
        }
    }
}