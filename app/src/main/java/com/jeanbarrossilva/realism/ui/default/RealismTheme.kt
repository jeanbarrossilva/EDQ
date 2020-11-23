package com.jeanbarrossilva.realism.ui.default

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.asFontFamily
import androidx.compose.ui.text.font.font
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.realism.R

object RealismTheme {
    private val typography = Typography(FontFamily.rubik)
    private val shapes = Shapes(Shape.small, Shape.medium, Shape.large)

    object Color {
        private val primary = Color(105, 240, 174)
        private val primaryVariant = Color(159, 255, 224)
        private val secondary = Color(46, 189, 126)

        object Palette {
            @Composable
            fun light() = lightColors(primary, primaryVariant, secondary)

            @Composable
            fun dark() = darkColors(primary, primaryVariant, secondary)

            @Composable
            fun dynamic() = if (isSystemInDarkTheme()) dark() else light()
        }

        @Composable
        fun PreferenceBackground() = if (isSystemInDarkTheme()) Color(40, 40, 40) else Color(245, 245, 245)
    }

    private object FontFamily {
        val rubik = font(R.font.rubik).asFontFamily()
    }

    private object Shape {
        val small = RoundedCornerShape(5.dp)
        val medium = RoundedCornerShape(10.dp)
        val large = RoundedCornerShape(15.dp)
    }

    @Composable
    fun Wrap(content: @Composable () -> Unit) = MaterialTheme(colors = Color.Palette.dynamic(), typography, shapes, content)

    @Composable
    fun OnSurface(content: @Composable () -> Unit) {
        Wrap {
            Surface(Modifier.fillMaxSize()) {
                content()
            }
        }
    }
}