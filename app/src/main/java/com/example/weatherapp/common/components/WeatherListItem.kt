package com.example.weatherapp.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherapp.featureweather.domain.model.Weather

@Composable
fun WeatherListItem(
    modifier: Modifier = Modifier,
    weather: Weather,
) {
    Box {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            weather.run {
                Text(
                    text = "#${id.toString()}",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(weather.icon)
                        .build(),
                    contentDescription = "icon"
                )

                Text(
                    text = temperatureInCelsius,
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = "$condition ($description)",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}