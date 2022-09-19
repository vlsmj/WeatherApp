package com.example.weatherapp.featureweather.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey val id: String,
    val location: String? = "",
    val temperatureInCelsius: String,
    val icon: String,
    val condition: String,
    val description: String,
    val sunriseTime: String,
    val sunsetTime: String,
)