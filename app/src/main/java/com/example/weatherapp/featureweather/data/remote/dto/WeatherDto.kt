package com.example.weatherapp.featureweather.data.remote.dto

import com.example.weatherapp.common.Constants
import com.example.weatherapp.common.Validate
import com.example.weatherapp.common.extensions.toDateFormat
import com.example.weatherapp.featureweather.domain.model.Weather
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

data class WeatherDto(
    val id: String,
    @SerializedName("weather") val conditionDto: List<ConditionDto>,
    @SerializedName("main") val temperatureDto: TemperatureDto,
    @SerializedName("sys") val countryDto: CountryDto,
)

fun WeatherDto.toWeather(): Weather {
    val currentHour = SimpleDateFormat(Constants.TIME_FORMAT, Locale.getDefault()).calendar.get(Calendar.HOUR_OF_DAY)
    val currentMinute = SimpleDateFormat(Constants.TIME_FORMAT, Locale.getDefault()).calendar.get(Calendar.MINUTE)

    val mTemperatureInCelsius = "${(this.temperatureDto.temp - 273.15f).roundToInt()}\u2103"
    val icon = "https://openweathermap.org/img/wn/${
        Validate.isPastSixEvening(
            this.conditionDto[0].icon,
            currentHour,
            currentMinute)
    }@2x.png"
    val condition = this.conditionDto[0].main
    val description = this.conditionDto[0].description
    val sunriseTime = this.countryDto.sunrise.toString()
    val sunsetTime = this.countryDto.sunset.toString()

    return Weather(
        temperatureInCelsius = mTemperatureInCelsius,
        icon = icon,
        condition = condition,
        description = description,
        sunriseTime = sunriseTime.toDateFormat(),
        sunsetTime = sunsetTime.toDateFormat()
    )
}