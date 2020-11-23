package com.jeanbarrossilva.realism.data

import com.google.gson.annotations.SerializedName

class QuotableQuoteResults(@SerializedName("results") val quotes: List<Quote>)