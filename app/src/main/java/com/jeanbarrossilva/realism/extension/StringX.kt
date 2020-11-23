package com.jeanbarrossilva.realism.extension

import java.time.LocalTime

object StringX {
    private val String.isTime get() = Regex("^([0-1][0-9]|[2][0-3]):([0-5][0-9])\$").matches(this)

    fun String.toLocalTime() =
        if (length == 5 && isTime) {
            val (hour, minute) = substring(0..1).toInt() to substring(3..4).toInt()
            LocalTime.of(hour, minute)
        } else
            null
}