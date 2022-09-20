package com.example.weatherapp.common.extensions

import com.example.weatherapp.common.Constants.DATE_FORMAT_DISPLAY
import java.text.SimpleDateFormat
import java.util.*

fun String.toDateFormat(): String {
    val sdf = SimpleDateFormat(DATE_FORMAT_DISPLAY, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()

    return sdf.format(Date(this.toLong() * 1000L))
}