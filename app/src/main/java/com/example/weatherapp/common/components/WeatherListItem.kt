package com.example.weatherapp.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weatherapp.featureweather.domain.model.Weather

@Composable
fun WeatherListItem(
    modifier: Modifier = Modifier,
    weather: Weather,
) {
    Box {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            weather.run {
                Text(text = id.toString())
                Text(text = condition)
            }
        }
    }
}