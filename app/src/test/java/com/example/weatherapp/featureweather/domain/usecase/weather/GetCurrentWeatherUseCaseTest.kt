package com.example.weatherapp.featureweather.domain.usecase.weather

import com.example.weatherapp.common.Validate
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GetCurrentWeatherUseCaseTest {

    @Test
    fun `Get current weather past 6 PM, return replace with night icon`() {
        val dayIcon = "https://openweathermap.org/img/wn/02d@2x.png"

        val icon = Validate.isPastSixEvening(dayIcon, 18, 1)
        assertThat(icon).isEqualTo("https://openweathermap.org/img/wn/02n@2x.png")

        val icon2 = Validate.isPastSixEvening(dayIcon, 20, 0)
        assertThat(icon2).isEqualTo("https://openweathermap.org/img/wn/02n@2x.png")
    }

    @Test
    fun `Get current weather not past 6 PM, return replace with day icon`() {
        val dayIcon = "https://openweathermap.org/img/wn/02d@2x.png"

        val icon = Validate.isPastSixEvening(dayIcon, 18, 0)
        assertThat(icon).isEqualTo("https://openweathermap.org/img/wn/02d@2x.png")

        val icon2 = Validate.isPastSixEvening(dayIcon, 17, 59)
        assertThat(icon2).isEqualTo("https://openweathermap.org/img/wn/02d@2x.png")
    }
}