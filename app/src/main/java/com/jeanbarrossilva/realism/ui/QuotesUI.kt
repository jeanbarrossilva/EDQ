package com.jeanbarrossilva.realism.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.ui.tooling.preview.Preview
import com.jeanbarrossilva.realism.ui.default.RealismTheme
import com.jeanbarrossilva.realism.R
import com.jeanbarrossilva.realism.data.Quote
import com.jeanbarrossilva.realism.repository.QuoteRepository
import com.jeanbarrossilva.realism.ui.component.LazyColumnOrHintFor
import com.jeanbarrossilva.realism.ui.component.QuoteCardFor

@Composable
@Preview
fun QuotesUI() {
    val quotes: List<Quote>? by QuoteRepository.quotes.observeAsState()
    val favoriteQuotes = quotes?.filter { it.isFavorite } ?: emptyList()

    RealismTheme.OnSurface {
        LazyColumnOrHintFor(
            favoriteQuotes,
            icon = Icons.Rounded.Favorite,
            title = stringResource(R.string.Advice_title_empty_favorite_quotes),
            description = stringResource(R.string.Advice_message_empty_favorite_quotes)
        ) { quote ->
            QuoteCardFor(quote)
        }
    }
}