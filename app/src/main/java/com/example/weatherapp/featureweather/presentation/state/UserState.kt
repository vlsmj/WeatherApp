package com.example.weatherapp.featureweather.presentation.state

import com.example.weatherapp.common.util.UiText
import com.example.weatherapp.featureweather.domain.model.User

data class UserState(
    val username: String? = "",
    val password: String? = "",
    val data: User? = null,
    val errorMessage: UiText? = UiText.DynamicString(""),
)
