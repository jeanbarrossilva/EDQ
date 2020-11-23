package com.jeanbarrossilva.realism.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jeanbarrossilva.realism.ui.component.PreferencesCategory
import com.jeanbarrossilva.realism.ui.component.Screen
import com.jeanbarrossilva.realism.ui.component.SwitchPreferenceFor
import com.jeanbarrossilva.realism.ui.component.TimePreferenceFor
import com.jeanbarrossilva.realism.data.RealismPreference.Companion.allowQuoteNotifications
import com.jeanbarrossilva.realism.data.RealismPreference.Companion.quoteNotificationTime
import com.jeanbarrossilva.realism.ui.default.RealismTheme
import com.jeanbarrossilva.realism.R

@Composable
fun PreferencesUI() {
    RealismTheme.OnSurface {
        Screen(name = stringResource(R.string.RealismScreen_route_preferences)) {
            PreferencesCategory(name = stringResource(R.string.preference_category_title_notifications)) {
                SwitchPreferenceFor(allowQuoteNotifications())

                TimePreferenceFor(
                    quoteNotificationTime(),
                    dependency = allowQuoteNotifications()
                )
            }
        }
    }
}