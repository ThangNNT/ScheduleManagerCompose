package com.nnt.schedulemanagercompose.extension

import com.nnt.schedulemanagercompose.AppConstant
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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

fun LocalDate.toString(format: String): String {
    val simpleDateFormat = DateTimeFormatter.ofPattern(format)
    return this.format(simpleDateFormat)
}

fun String.toLocalDate(pattern: String): LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ofPattern(pattern))
}

fun LocalDate.toStandardString() = this.toString(AppConstant.DATE_STANDARD_PATTERN)
fun String.fromStandardToLocalDate() = this.toLocalDate(AppConstant.DATE_STANDARD_PATTERN)