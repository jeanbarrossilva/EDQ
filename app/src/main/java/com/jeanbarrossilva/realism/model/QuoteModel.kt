package com.jeanbarrossilva.realism.model

import android.content.Context
import android.util.Log
import com.jeanbarrossilva.realism.data.RealismPreference
import com.jeanbarrossilva.realism.data.service.QuotableService
import com.jeanbarrossilva.realism.extension.ContextX.connectivityManager
import com.jeanbarrossilva.realism.repository.QuoteRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QuoteModel {
    private val gsonConverterFactory = GsonConverterFactory.create()
    private val retrofit = Retrofit.Builder().addConverterFactory(gsonConverterFactory).baseUrl("https://staging.quotable.io").build()
    private val service = retrofit.create(QuotableService::class.java)

    private val authors = listOf("Aristotle", "Epictetus", "Marcus Aurelius", "Plato", "Socrates")

    fun fetch(context: Context) {
        val didFetchQuotes = RealismPreference(context, key = "didFetchQuotes", defaultValue = false)
    }
}