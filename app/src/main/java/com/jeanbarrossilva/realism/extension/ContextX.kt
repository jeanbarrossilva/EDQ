package com.jeanbarrossilva.realism.extension

import android.app.AlarmManager
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.preference.PreferenceManager

object ContextX {
    val Context.alarmManager: AlarmManager get() = getSystemService(AlarmManager::class.java)
    val Context.connectivityManager: ConnectivityManager get() = getSystemService(ConnectivityManager::class.java)
    val Context.notificationManager: NotificationManager get() = getSystemService(NotificationManager::class.java)
    val Context.preferences: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(this)
}