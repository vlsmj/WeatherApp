package com.example.weatherapp.featureweather.data.remote.dto

import com.example.weatherapp.featureweather.domain.model.Weather
import com.google.gson.annotations.SerializedName

data class WeatherDto(
    val id: String,
    @SerializedName("weather") val conditionDto: List<ConditionDto>,
    @SerializedName("main") val temperatureDto: TemperatureDto,
    @SerializedName("sys") val countryDto: CountryDto,
)

fun WeatherDto.toWeather(): Weather {
    val mTemperatureInCelsius = this.temperatureDto.temp.toString()
    val icon = this.conditionDto[0].icon
    val condition = this.conditionDto[0].main
    val description = this.conditionDto[0].description
    val sunriseTime = this.countryDto.sunrise.toString()
    val sunsetTime = this.countryDto.sunset.toString()

    return Weather(
        temperatureInCelsius = mTemperatureInCelsius,
        icon = icon,
        condition = condition,
        description = description,
        sunriseTime = sunriseTime,
        sunsetTime = sunsetTime
    )
}