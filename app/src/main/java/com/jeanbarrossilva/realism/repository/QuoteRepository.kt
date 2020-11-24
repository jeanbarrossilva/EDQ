package com.jeanbarrossilva.realism.repository

import com.jeanbarrossilva.realism.RealismApplication.Companion.database
import com.jeanbarrossilva.realism.data.Quote
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object QuoteRepository {
    val default = mutableListOf(
        Quote.default("Among the ignorant, the wrong is the new right."),
        Quote.default("Dependence on motivation creates stagnation."),
        Quote.default("An idea that is not validated is worthless."),
        Quote.default("Don't act with intention; act with action."),
        Quote.default("Faith does not replace action."),
        Quote.default("Don't say it, do it."),
        Quote.default("You are what you do.")
    )

    val quotes = database.quoteDao().all()

    fun quotes() = quotes.value ?: emptyList()

    fun add(quote: Quote) {
        GlobalScope.launch {
            database.quoteDao().add(quote)
        }
    }

    fun update(quote: Quote, block: Quote.() -> Unit) {
        block(quote)
        database.quoteDao().update(quote)
    }

    init {
        default.forEach { quote ->
            add(quote)
        }
    }
}