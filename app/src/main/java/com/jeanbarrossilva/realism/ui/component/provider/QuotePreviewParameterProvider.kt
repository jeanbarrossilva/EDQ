package com.jeanbarrossilva.realism.ui.component.provider

import androidx.ui.tooling.preview.PreviewParameterProvider
import com.jeanbarrossilva.realism.data.Quote
import com.jeanbarrossilva.realism.repository.QuoteRepository

class QuotePreviewParameterProvider : PreviewParameterProvider<Quote> {
    override val values = QuoteRepository.default.asSequence()
}