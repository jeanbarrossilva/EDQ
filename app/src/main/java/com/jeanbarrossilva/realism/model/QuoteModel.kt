package com.jeanbarrossilva.realism.model

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import com.jeanbarrossilva.realism.data.Quote
import com.jeanbarrossilva.realism.data.service.QuotableService
import com.jeanbarrossilva.realism.extension.ContextX.connectivityManager
import com.jeanbarrossilva.realism.extension.ContextX.preferences
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

object QuoteModel {
    private val gsonConverterFactory = GsonConverterFactory.create()
    private val retrofit = Retrofit.Builder().addConverterFactory(gsonConverterFactory).baseUrl("https://staging.quotable.io").build()
    private val service = retrofit.create(QuotableService::class.java)

    private val authors = listOf("Aristotle", "Epictetus", "Marcus Aurelius", "Plato", "Socrates")

    private val quotes = mutableListOf(
        Quote.mine("Among the ignorant, the wrong is the new right."),
        Quote.mine("Dependence on motivation creates stagnation."),
        Quote.mine("An idea that is not validated is worthless."),
        Quote.mine("Don't act with intention; act with action."),
        Quote.mine("Faith does not replace action."),
        Quote.mine("Don't say it, do it."),
        Quote.mine("You are what you do.")
    )

    fun fetch(context: Context) {
        context.preferences.getBoolean("didFetchQuotes", false).let { didFetch ->
            if (!didFetch && context.connectivityManager.isDefaultNetworkActive)
                GlobalScope
                    .launch {
                        authors.forEach { author ->
                            service.getQuotesFrom(author).awaitResponse().body()?.quotes?.let { fetchedQuotes ->
                                quotes.addAll(fetchedQuotes)
                            }
                        }
                    }
                    .invokeOnCompletion { error ->
                        context.preferences.edit { putBoolean("didFetchQuotes", error == null) }
                        if (error == null) Log.d("QuoteModel.fetch", "Fetched ${quotes.size} quotes:\n${quotes.joinToString("\n")}")
                    }
        }
    }

    fun allQuotes() = quotes.toList()
    
    fun favoriteQuotes() = allQuotes().filter { it.isFavorite }
    
    fun unreadQuotes(): List<Quote> {
        val unreadQuotes = allQuotes().filterNot { it.isRead }
        val areAllRead = unreadQuotes.isEmpty()

        return if (areAllRead) allQuotes() else unreadQuotes
    }
}