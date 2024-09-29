package com.example.weatherapp.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalAppDimens = staticCompositionLocalOf {
    Dimensions()
}

@Suppress("ConstructorParameterNaming", "PropertyName")
@Immutable
data class Dimensions(
    val s_2: Dp = 2.dp,
    val s_4: Dp = 4.dp,
    val s_6: Dp = 6.dp,
    val s_8: Dp = 8.dp,
    val s_10: Dp = 10.dp,
    val s_12: Dp = 12.dp,
    val s_14: Dp = 14.dp,
    val s_16: Dp = 16.dp,
    val s_20: Dp = 20.dp,
    val s_24: Dp = 24.dp,
    val s_32: Dp = 32.dp,
    val s_36: Dp = 36.dp,
    val s_40: Dp = 40.dp,
    val s_48: Dp = 48.dp,
    val s_56: Dp = 56.dp,
    val s_64: Dp = 64.dp,
    val s_76: Dp = 76.dp,
    val s_88: Dp = 88.dp,
    val s_100: Dp = 100.dp,
)