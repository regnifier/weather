package com.example.weatherapp.presentation.detail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.utils.getTimeFromString
import com.example.weatherapp.utils.isToday


@Composable
fun DetailScreenContent(
    modifier: Modifier = Modifier,
    weatherInfo: Weather,
    cityName: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = cityName,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = weatherInfo.current.time,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(R.drawable.ic_weather),
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(
                R.string.temperature_value_in_celsius,
                weatherInfo.current.temperature2m
            ),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
        ) {
            WeatherComponent(
                modifier = Modifier.weight(1f),
                weatherLabel = stringResource(R.string.wind_speed_label),
                weatherValue = weatherInfo.current.windSpeed10m.toString(),
                weatherUnit = stringResource(R.string.wind_speed_unit),
                iconId = R.drawable.ic_wind,
            )
            WeatherComponent(
                modifier = Modifier.weight(1f),
                weatherLabel = stringResource(R.string.pressure_label),
                weatherValue = weatherInfo.current.pressureMsl.toString(),
                weatherUnit = stringResource(R.string.pressure_unit),
                iconId = R.drawable.ic_pressure,
            )
            WeatherComponent(
                modifier = Modifier.weight(1f),
                weatherLabel = stringResource(R.string.humidity_label),
                weatherValue = weatherInfo.current.relativeHumidity2m.toString(),
                weatherUnit = stringResource(R.string.humidity_unit),
                iconId = R.drawable.ic_uv,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.hourly_forecast_text),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(weatherInfo.hourlyInfo) { weatherData ->
                if (weatherData.first.isToday()) {
                    WeatherItem(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        weatherDate = weatherData.first.getTimeFromString(),
                        weatherValue = weatherData.second
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.daily_forecast_text),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(weatherInfo.dailyInfo) { weatherData ->
                WeatherItem(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    weatherDate = weatherData.first,
                    weatherValue = weatherData.second
                )
            }
        }
    }
}

@Composable
private fun WeatherComponent(
    modifier: Modifier = Modifier,
    weatherLabel: String,
    weatherValue: String,
    weatherUnit: String,
    iconId: Int,
) {
    ElevatedCard(
        modifier = modifier.padding(end = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = weatherLabel,
                style = MaterialTheme.typography.bodySmall,
            )
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = iconId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Text(
                text = weatherValue,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = weatherUnit,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}