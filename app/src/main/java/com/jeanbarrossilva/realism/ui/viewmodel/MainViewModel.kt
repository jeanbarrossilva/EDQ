package com.jeanbarrossilva.realism.ui.viewmodel

import android.app.AlarmManager.INTERVAL_DAY
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.jeanbarrossilva.realism.MainActivity
import com.jeanbarrossilva.realism.R
import com.jeanbarrossilva.realism.broadcast.QuoteNotificationBroadcastReceiver
import com.jeanbarrossilva.realism.extension.ContextX.alarmManager
import com.jeanbarrossilva.realism.extension.ContextX.notificationManager
import com.jeanbarrossilva.realism.extension.StringX.toLocalTime
import com.jeanbarrossilva.realism.data.RealismPreference
import com.jeanbarrossilva.realism.extension.AlarmManagerX.broadcast

class MainViewModel(private val activity: MainActivity) : ViewModel() {
    fun configAppearance() {
        activity.window?.statusBarColor = Color.BLACK
        activity.window?.navigationBarColor = Color.BLACK
    }

    @Composable
    fun configNotifications() {
        val (name, description) =
            activity.getString(R.string.notification_channel_name_quotes) to activity.getString(R.string.notification_channel_description_quotes)

        NotificationChannel(NOTIFICATION_CHANNEL_ID_QUOTES, name, NotificationManager.IMPORTANCE_MIN)
            .apply { this.description = description }
            .let { channel ->
                RealismPreference.allowQuoteNotifications().onChange { isActivated ->
                    with(activity.notificationManager) {
                        if (isActivated) createNotificationChannel(channel) else deleteNotificationChannel(channel.id)
                    }
                }
            }

        RealismPreference.quoteNotificationTime().onChange {
            activity.alarmManager.broadcast(
                activity,
                receiver = QuoteNotificationBroadcastReceiver::class,
                INTERVAL_DAY,
                time = RealismPreference.quoteNotificationTime().value().toLocalTime()
            )
        }
    }

    companion object {
        const val NOTIFICATION_CHANNEL_ID_QUOTES = "quotes"
    }
}