package com.example.weatherapp

import androidx.compose.ui.graphics.Color
import com.example.weatherapp.utils.getLocalDateFromString
import com.example.weatherapp.utils.getTemperatureColor
import com.example.weatherapp.utils.isToday
import com.example.weatherapp.utils.letters
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ExtensionsTest {

    @Test
    fun getLocalDateFromStringValidInputTest() {
        val inputDate = "2024-09-29T15:30"
        val result = inputDate.getLocalDateFromString()
        val expectedDate = "29-Sep-2024"
        assertEquals(expectedDate, result)
    }

    @Test(expected = java.time.format.DateTimeParseException::class)
    fun getLocalDateFromStringInvalidInputTest() {
        val invalidInput = "2024/09/29"
        invalidInput.getLocalDateFromString()
    }

    @Test
    fun isTodayWithTodayDateTest() {
        val today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
        val result = today.isToday()
        assertTrue(result)
    }

    @Test
    fun isTodayWithPastDateTest() {
        val pastDate = "2023-09-28T15:30"
        val result = pastDate.isToday()
        assertFalse(result)
    }

    @Test
    fun isTodayWithFutureDateTest() {
        val futureDate = "2025-12-31T23:59"
        val result = futureDate.isToday()
        assertFalse(result)
    }

    @Test
    fun stringWithOnlyLettersTest() {
        val input = "HelloWorld"
        val result = input.letters()
        assertEquals("HelloWorld", result)
    }

    @Test
    fun stringWithLettersAndNumbersTest() {
        val input = "H3ll0W0rld123"
        val result = input.letters()
        assertEquals("HllWrld", result)
    }

    @Test
    fun stringWithSpecialCharactersTest() {
        val input = "H@e#l%l^o&*W(orld)"
        val result = input.letters()
        assertEquals("HelloWorld", result)
    }

    @Test
    fun emptyStringTest() {
        val input = ""
        val result = input.letters()
        assertEquals("", result)
    }

    @Test
    fun getTemperatureColorLogicForTemperatureBelow10Test() {
        val temperature = 5.0
        val result = temperature.getTemperatureColor()
        assertEquals(Color.Blue, result)
    }

    @Test
    fun getTemperatureColorLogicForTemperatureBetween10And20Test() {
        val temperature = 15.0
        val result = temperature.getTemperatureColor()
        assertEquals(Color.Black, result)
    }


    @Test
    fun getTemperatureColorLogicForTemperatureAbove10Test() {
        val temperature = 25.0
        val result = temperature.getTemperatureColor()
        assertEquals(Color.Red, result)
    }
}