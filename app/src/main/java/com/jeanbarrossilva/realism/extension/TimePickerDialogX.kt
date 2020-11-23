package com.jeanbarrossilva.realism.extension

import android.app.TimePickerDialog
import android.content.Context
import java.time.LocalTime

object TimePickerDialogX {
    inline fun timePicker(
        context: Context,
        defaultTime: LocalTime?,
        is24Hours: Boolean = true,
        crossinline block: (hour: Int, minute: Int) -> Unit = { _, _ -> }
    ) {
        defaultTime?.let { time ->
            TimePickerDialog(context, { _, hour, minute -> block(hour, minute) }, time.hour, time.minute, is24Hours).show()
        }
    }
}