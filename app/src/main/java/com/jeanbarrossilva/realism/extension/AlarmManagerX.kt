package com.jeanbarrossilva.realism.extension

import android.app.AlarmManager
import android.app.AlarmManager.RTC
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.time.LocalTime
import java.util.Calendar
import java.util.Calendar.HOUR_OF_DAY
import java.util.Calendar.MINUTE
import kotlin.reflect.KClass

object AlarmManagerX {
    fun AlarmManager.broadcast(context: Context, receiver: KClass<out BroadcastReceiver>, interval: Long, time: LocalTime) {
        val calendar = Calendar.getInstance().apply {
            set(HOUR_OF_DAY, time.hour)
            set(MINUTE, time.minute)
        }

        Intent(context, receiver.java)
            .let { intent -> PendingIntent.getBroadcast(context, 0, intent, FLAG_UPDATE_CURRENT) }
            .let { pendingIntent -> setInexactRepeating(RTC, calendar.timeInMillis, interval, pendingIntent) }
    }
}