package com.jeanbarrossilva.realism.data

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.stringResource
import androidx.core.content.edit
import com.jeanbarrossilva.realism.extension.ContextX.preferences
import com.jeanbarrossilva.realism.R
import com.jeanbarrossilva.realism.extension.SharedPreferencesEditorX.add
import java.time.LocalTime

@Suppress("UNCHECKED_CAST")
class RealismPreference<T> private constructor(
    private val context: Context,
    val key: String,
    val defaultValue: T,
    val name: String,
    val dependency: RealismPreference<Boolean>? = null
) {
    private val preferences = context.preferences
    private val doesSharedPreferenceExist = preferences.contains(key)

    /** Gets the value from [context]'s [android.content.SharedPreferences]. **/
    fun value(): T? = preferences.all[key] as T?

    @Composable
    fun onChange(block: @Composable (T?) -> Unit) {
        block(value())
        onChangeListeners.add(key to block as @Composable (Any?) -> Unit)
    }

    fun isAvailable() = dependency?.let { it.value() == true } ?: true

    init {
        if (!doesSharedPreferenceExist) {
            preferences.edit { add(defaultValue to key) }
            Log.d("RealismPreference", "Tried to access nonexistent $key SharedPreference. It has been added with the value of ${value()}.")
        }
    }

    companion object {
        private val onChangeListeners = mutableListOf<Pair<String, @Composable (Any?) -> Unit>>()

        @Composable
        fun <T> setValueOf(valueSet: Pair<RealismPreference<T>, T?>) {
            val (preference, value) = valueSet

            ContextAmbient.current.preferences.edit(commit = true) {
                add(value to preference.key)
            }

            with(onChangeListeners) {
                forEach { (key, function) -> if (key == preference.key) function(value) }
                lastOrNull { (key, _) -> key == key }?.let { (key, _) -> Log.d("RealismPreference.setValueOf", "$key changed to $value.") }
            }
        }

        @Composable
        fun allowQuoteNotifications() = RealismPreference(
            ContextAmbient.current,
            key = "allowQuoteNotifications",
            defaultValue = true,
            name = stringResource(R.string.RealismPreference_name_allowQuoteNotifications)
        )

        @Composable
        fun quoteNotificationTime() = RealismPreference(
            ContextAmbient.current,
            key = "quoteNotificationTime",
            defaultValue = LocalTime.of(0, 0).toString(),
            name = stringResource(R.string.RealismPreference_name_quoteNotificationTime),
            dependency = allowQuoteNotifications()
        )
    }
}