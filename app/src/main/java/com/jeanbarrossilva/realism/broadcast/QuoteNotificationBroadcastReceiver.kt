package com.jeanbarrossilva.realism.broadcast

import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.jeanbarrossilva.realism.R
import com.jeanbarrossilva.realism.extension.ContextX.notificationManager
import com.jeanbarrossilva.realism.repository.QuoteRepository
import com.jeanbarrossilva.realism.ui.viewmodel.MainViewModel

class QuoteNotificationBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val unreadQuotes = QuoteRepository.quotes().filterNot { it.isRead }
        val quote = unreadQuotes.random()

        val favoriteQuoteActionIntent = Intent(context, QuoteNotificationFavoriteQuoteActionBroadcastReceiver::class.java)
        val favoriteQuoteActionPendingIntent = PendingIntent.getBroadcast(context, 0, favoriteQuoteActionIntent, 0)
        val favoriteQuoteActionTitle = context?.getString(R.string.notification_action_quotes_favorite)
        val favoriteQuoteAction = Notification.Action.Builder(null, favoriteQuoteActionTitle, favoriteQuoteActionPendingIntent).build()

        Notification.Builder(context, MainViewModel.NOTIFICATION_CHANNEL_ID_QUOTES)
            .setSmallIcon(R.drawable.ic_format_quote)
            .setContentTitle(quote.content)
            .setContentText(quote.author)
            .addAction(favoriteQuoteAction)
            .build()
            .let { notification ->
                context?.notificationManager?.notify(quote.id.toInt(), notification)
                QuoteRepository.update(quote) { isRead = true }
            }
            .also { favoriteQuoteActionIntent.putExtra(INTENT_EXTRA_KEY_FAVORITE_QUOTE, quote) }
    }

    companion object {
        const val INTENT_EXTRA_KEY_FAVORITE_QUOTE = "quote"
    }
}