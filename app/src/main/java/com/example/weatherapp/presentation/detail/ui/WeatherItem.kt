package com.example.weatherapp.presentation.detail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.utils.getTemperatureColor

@Composable
fun WeatherItem(
    modifier: Modifier = Modifier,
    weatherDate: String,
    weatherValue: Double
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = weatherDate,
                style = MaterialTheme.typography.titleMedium
            )
            Image(
                painter = painterResource(id = R.drawable.ic_sun),
                contentDescription = null,
                modifier = Modifier
                    .width(40.dp)
                    .padding(4.dp)
            )
            Text(
                text = stringResource(
                    R.string.temperature_value_in_celsius,
                    weatherValue
                ),
                style = MaterialTheme.typography.titleMedium,
                color = weatherValue.getTemperatureColor()
            )
        }
    }
}