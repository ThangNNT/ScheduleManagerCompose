package com.nnt.schedulemanagercompose.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ThangNNT on 02/12/2023.
 */
fun Long.toDateString(format: String = "dd/MM/yyyy"): String {
    if (this == 0L) return ""
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    val simpleDateFormat = SimpleDateFormat(format, Locale.ROOT)
    return try {
        simpleDateFormat.format(calendar.time)
    } catch (ex: Exception) {
        ex.printStackTrace()
        ""
    }
}