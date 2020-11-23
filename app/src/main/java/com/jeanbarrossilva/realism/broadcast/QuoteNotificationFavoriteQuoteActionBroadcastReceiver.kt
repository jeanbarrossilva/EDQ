package com.jeanbarrossilva.realism.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.jeanbarrossilva.realism.data.Quote

class QuoteNotificationFavoriteQuoteActionBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        (intent?.getSerializableExtra(QuoteNotificationBroadcastReceiver.INTENT_EXTRA_KEY_FAVORITE_QUOTE) as? Quote)?.let { quote ->
            quote.isFavorite = true
        }
    }
}