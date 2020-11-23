package com.jeanbarrossilva.realism.extension

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.realism.ui.default.RealismTheme

object ModifierX {
    @Composable
    fun PreferenceModifier() =
        Modifier
            .fillMaxWidth()
            .background(color = RealismTheme.Color.PreferenceBackground(), shape = MaterialTheme.shapes.large)
            .padding(vertical = 20.dp, horizontal = 30.dp)
}