package com.weatherapp.basic.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Convert String date & time to readable format
 */
fun Int.formatTime() : String {

    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this*1000.toLong()

        val dateFormat = SimpleDateFormat("dd MMM, yyyy - hh:mm a", Locale.ENGLISH)
        dateFormat.timeZone = TimeZone.getDefault()
        return dateFormat.format(calendar.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return this.toString()
}