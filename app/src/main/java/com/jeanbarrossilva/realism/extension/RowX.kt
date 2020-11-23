package com.jeanbarrossilva.realism.extension

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jeanbarrossilva.realism.extension.ModifierX.PreferenceModifier

object RowX {
    @Composable
    fun PreferenceRow(modifier: Modifier = PreferenceModifier(), content: @Composable () -> Unit) {
        Row(
            modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}