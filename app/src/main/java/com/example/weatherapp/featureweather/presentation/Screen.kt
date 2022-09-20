package com.example.weatherapp.featureweather.presentation

sealed class Screen(val route: String) {
    object CurrentWeatherScreen : Screen("weather_current_screen?username={username}")
    object ListWeatherScreen : Screen("weather_list_screen")
}
