package com.jeanbarrossilva.realism.ui.default

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.realism.ui.PreferencesUI
import com.jeanbarrossilva.realism.ui.QuotesUI
import com.jeanbarrossilva.realism.R

@Suppress("Unused")
sealed class RealismScreen(val route: String, @StringRes val labelRes: Int, val ui: @Composable () -> Unit, val showsFab: Boolean = true) {
    object QuotesScreen : RealismScreen("quotes", R.string.RealismScreen_label_quotes, { QuotesUI() }, showsFab = true)
    object PreferencesScreen : RealismScreen("preferences", R.string.RealismScreen_route_preferences, { PreferencesUI() }, showsFab = false)

    companion object {
        val values = RealismScreen::class.sealedSubclasses.map { it.objectInstance!! }
    }
}