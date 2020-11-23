package com.jeanbarrossilva.realism.extension

import java.text.NumberFormat

object IntX {
    private val doubleDigitFormat = NumberFormat.getInstance().apply {
        minimumIntegerDigits = 2
        maximumIntegerDigits = 2
    }

    val Int.withDoubleDigit: String get() = doubleDigitFormat.format(this)
}