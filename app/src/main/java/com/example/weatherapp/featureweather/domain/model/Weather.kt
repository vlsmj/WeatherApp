package com.example.weatherapp.featureweather.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.common.Constants.DEFAULT_LATITUDE
import com.example.weatherapp.common.Constants.DEFAULT_LONGITUDE

@Entity
data class Weather(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var location: String? = "",
    var latitude: Double? = DEFAULT_LATITUDE,
    var longitude: Double? = DEFAULT_LONGITUDE,
    val temperatureInCelsius: String,
    val icon: String,
    val condition: String,
    val description: String,
    val sunriseTime: String,
    val sunsetTime: String,
)