package com.jeanbarrossilva.realism.data.service

import com.jeanbarrossilva.realism.data.QuotableQuoteResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotableService {
    @GET("/quotes")
    fun getQuotesFrom(@Query("author") author: String): Call<QuotableQuoteResults>

    companion object {
        const val URL = "https://api.quotable.io"
    }
}