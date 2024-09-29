package com.example.weatherapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
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

fun String.letters() = filter { it.isLetter() }

fun Double.getTemperatureColor(): Color {
    return when {
        this < 10.0 -> Color.Blue
        10.0 < this && this < 20.0 -> Color.Black
        this > 20.0 -> Color.Red
        else -> Color.Black
    }
}