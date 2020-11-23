package com.jeanbarrossilva.realism.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.realism.ui.default.RealismTheme

@Composable
fun PreferencesCategory(name: String? = null, content: @Composable () -> Unit) {
    RealismTheme.Wrap {
        Column(
            Modifier.padding(top = 10.dp, start = 25.dp, end = 25.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            name?.let {
                Text(
                    name,
                    Modifier
                        .drawOpacity(0.5f)
                        .padding(start = 10.dp)
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                content()
            }
        }
    }
}