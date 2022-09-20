package com.example.weatherapp.featureweather.presentation

sealed class Screen(val route: String) {
    object CurrentWeatherScreen : Screen("weather_current_screen?username={username}&lat={lat}&lat={lon}&lat={address}")
    object ListWeatherScreen : Screen("weather_list_screen")
}
