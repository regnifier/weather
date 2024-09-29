package com.example.weatherapp.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import kotlinx.coroutines.delay

private const val SPLASH_DELAY = 1500L
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToSearchScreen: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_weather),
            contentDescription = "splash_image",
            modifier = Modifier.size(200.dp)
        )
    }

    LaunchedEffect(key1 = true) {
        delay(SPLASH_DELAY)
        onNavigateToSearchScreen()
    }
}