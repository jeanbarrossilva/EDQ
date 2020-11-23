package com.jeanbarrossilva.realism.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.stringResource
import androidx.ui.tooling.preview.Preview
import com.jeanbarrossilva.realism.ui.component.LazyColumnOrAdviceFor
import com.jeanbarrossilva.realism.ui.default.RealismTheme
import com.jeanbarrossilva.realism.R
import com.jeanbarrossilva.realism.model.QuoteModel
import com.jeanbarrossilva.realism.model.QuoteModel.favoriteQuotes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
@Preview
fun QuotesUI() {
    RealismTheme.OnSurface {
        val context = ContextAmbient.current
        GlobalScope.launch { QuoteModel.fetch(context) }

        LazyColumnOrAdviceFor(
            favoriteQuotes(),
            icon = Icons.Rounded.Favorite,
            title = stringResource(R.string.Advice_title_empty_favorite_quotes),
            description = stringResource(R.string.Advice_message_empty_favorite_quotes)
        )
    }
}