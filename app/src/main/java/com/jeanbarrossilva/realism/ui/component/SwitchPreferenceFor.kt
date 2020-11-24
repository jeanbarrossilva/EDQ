package com.jeanbarrossilva.realism.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.jeanbarrossilva.realism.data.RealismPreference
import com.jeanbarrossilva.realism.extension.RowX.PreferenceRow
import com.jeanbarrossilva.realism.ui.default.RealismTheme

@Composable
fun SwitchPreferenceFor(preference: RealismPreference<Boolean>, editsOnCheckedChange: Boolean = true, onCheckedChange: ((Boolean) -> Unit)? = null) {
    if (preference.isAvailable()) {
        val (isActivated, setActivated) = remember { mutableStateOf(preference.value()) }

        if (editsOnCheckedChange)
            RealismPreference.setValueOf(preference to isActivated)

        RealismTheme.Wrap {
            PreferenceRow {
                Text(
                    preference.name!!,
                    Modifier.fillMaxWidth(fraction = 0.7f)
                )

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Switch(
                        checked = isActivated,
                        onCheckedChange = { isChecked ->
                            setActivated(isChecked)
                            onCheckedChange?.invoke(isChecked)
                        }
                    )
                }
            }
        }
    }
}