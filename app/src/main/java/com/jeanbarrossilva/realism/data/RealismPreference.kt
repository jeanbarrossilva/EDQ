package com.jeanbarrossilva.realism.data

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.stringResource
import androidx.core.content.edit
import com.jeanbarrossilva.realism.extension.ContextX.preferences
import com.jeanbarrossilva.realism.R
import com.jeanbarrossilva.realism.extension.SharedPreferencesEditorX.put
import java.time.LocalTime

@Suppress("UNCHECKED_CAST")
class RealismPreference<T>(
    private val context: Context,
    val key: String,
    val defaultValue: T,
    val name: String? = null,
    val dependency: RealismPreference<Boolean>? = null
) {
    private val preferences = context.preferences
    private val doesSharedPreferenceExist = preferences.contains(key)

    /** Gets the value from [context]'s [android.content.SharedPreferences]. **/
    fun value() = preferences.all[key] as T

    fun setValue(context: Context, value: T) = context.preferences.edit(commit = true) { put(key, value) }

    @Composable
    fun setValue(value: T) {
        setValue(ContextAmbient.current, value)

        with(onChangeListeners) {
            forEach { (key, function) -> if (key == this@RealismPreference.key) function(value) }
            lastOrNull { (key, _) -> key == key }?.let { (key, _) -> Log.d("RealismPreference.setValueOf", "$key changed to $value.") }
        }
    }

    @Composable
    fun onChange(block: @Composable (T) -> Unit) {
        block(value())
        onChangeListeners.add(key to block as @Composable (Any?) -> Unit)
    }

    fun isAvailable() = dependency?.value() ?: true

    init {
        if (!doesSharedPreferenceExist) {
            preferences.edit { put(key, defaultValue) }
            Log.d("RealismPreference", "Tried to access nonexistent $key SharedPreference. It has been added with the value of ${value()}.")
        }
    }

    companion object {
        private val onChangeListeners = mutableListOf<Pair<String, @Composable (Any?) -> Unit>>()

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
            defaultValue = LocalTime.of(9, 0).toString(),
            name = stringResource(R.string.RealismPreference_name_quoteNotificationTime),
            dependency = allowQuoteNotifications()
        )
    }
}