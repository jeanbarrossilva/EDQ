package com.jeanbarrossilva.realism.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeanbarrossilva.realism.ui.default.RealismTheme

@Composable
fun <T> LazyColumnOrHintFor(
    items: List<T>,
    modifier: Modifier = Modifier,
    icon: VectorAsset,
    title: String,
    description: String,
    content: @Composable ((T) -> Unit)? = null
) {
    RealismTheme.Wrap {
        if (items.isEmpty())
            Column(
                modifier,
                verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    icon,
                    Modifier.preferredSize(30.dp).drawOpacity(0.7f)
                )

                Column(
                    Modifier.padding(horizontal = 40.dp),
                    verticalArrangement = Arrangement.spacedBy(7.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        title,
                        Modifier.drawOpacity(0.5f),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        description,
                        Modifier.drawOpacity(0.5f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        else
            LazyColumnFor(items, modifier) { item -> content?.invoke(item) }
    }
}