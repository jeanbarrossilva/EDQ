package com.jeanbarrossilva.realism.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.realism.extension.IntX.withDoubleDigit
import com.jeanbarrossilva.realism.extension.StringX.toLocalTime
import com.jeanbarrossilva.realism.extension.TimePickerDialogX.timePicker
import com.jeanbarrossilva.realism.data.RealismPreference
import com.jeanbarrossilva.realism.extension.RowX.PreferenceRow
import com.jeanbarrossilva.realism.ui.default.RealismTheme
import com.jeanbarrossilva.realism.extension.ModifierX.PreferenceModifier
import java.time.LocalTime

@Composable
fun TimePreferenceFor(preference: RealismPreference<String>, dependency: RealismPreference<Boolean>? = null) {
    val context = ContextAmbient.current
    val (opacity, setOpacity) = remember { mutableStateOf(if (preference.isAvailable()) 1f else 0f) }
    val (time, setTime) = remember { mutableStateOf(preference.value()?.toLocalTime()) }

    RealismPreference.setValueOf(preference to "${time?.hour?.withDoubleDigit}:${time?.minute?.withDoubleDigit}")

    RealismTheme.Wrap {
        PreferenceRow(
            Modifier
                .drawOpacity(opacity)
                .clickable(enabled = preference.isAvailable()) {
                    timePicker(context, time) { hour, minute ->
                        setTime(LocalTime.of(hour, minute))
                    }
                }
                .then(PreferenceModifier())
                .also {
                    dependency?.onChange {
                        setOpacity(if (preference.isAvailable()) 1f else 0f)
                    }
                }
        ) {
            Text(
                preference.name!!,
                Modifier.fillMaxWidth(fraction = 0.5f)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.End)) {
                Text(
                    "${time?.hour?.withDoubleDigit}:${time?.minute?.withDoubleDigit}",
                    Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )

                Icon(
                    Icons.Rounded.KeyboardArrowRight,
                    Modifier.drawOpacity(0.5f)
                )
            }
        }
    }
}