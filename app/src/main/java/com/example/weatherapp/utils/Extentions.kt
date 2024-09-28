package com.example.weatherapp.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun String.getLocalDateFromString(): String {
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
    val localDate = LocalDate.parse(this, format)
    return localDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
}

fun String.isToday(): Boolean {
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
    val localDate = LocalDate.parse(this, format)
    return localDate == LocalDate.now()
}

fun String.getTimeFromString(): String {
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
    val localDateTime = LocalDateTime.parse(this, format)
    return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
}