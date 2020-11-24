package com.jeanbarrossilva.realism.extension

import android.content.SharedPreferences
import java.math.BigDecimal

object SharedPreferencesEditorX {
    @Suppress("UNCHECKED_CAST")
    fun <T> SharedPreferences.Editor.put(key: String, value: T) {
        fun crash() = put(key, BigDecimal(0))

        when (value) {
            is Boolean? -> putBoolean(key, value == true)
            is Float? -> putFloat(key, value ?: 0f)
            is Int? -> putInt(key, value ?: 0)
            is Long? -> putLong(key, value ?: 0L)
            is String? -> putString(key, value)
            is Set<*> -> if (value.all { it is String }) putStringSet(key, value as Set<String>) else crash()
            else -> throw IllegalArgumentException("Value $value of type ${runCatching { value!!::class.simpleName }.getOrNull()} is not supported " +
                "by any of SharedPreferences.Editor's \"put\" methods.")
        }
    }
}