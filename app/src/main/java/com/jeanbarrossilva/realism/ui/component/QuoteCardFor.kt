package com.jeanbarrossilva.realism.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import com.jeanbarrossilva.realism.data.Quote
import com.jeanbarrossilva.realism.ui.component.provider.QuotePreviewParameterProvider

@Composable
@Preview
fun QuoteCardFor(@PreviewParameter(QuotePreviewParameterProvider::class) quote: Quote) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically)) {
            Text(quote.content, fontWeight = FontWeight.Bold)
            Text(quote.author)
        }
    }
}