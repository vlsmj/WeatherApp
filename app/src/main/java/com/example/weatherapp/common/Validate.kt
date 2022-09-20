package com.example.weatherapp.common

import com.example.weatherapp.common.Constants.TIME_SIX_EVENING

object Validate {

    fun usernameAndPassword(username: String?, password: String?): String {
        if (username?.isBlank() == true) {
            return "Please enter your username."
        } else if (password?.isBlank() == true) {
            return "Please enter your password."
        }

        return ""
    }

    fun isPastSixEvening(icon: String, currentHour: Int, currentMinute: Int): String {
        return if (
            TIME_SIX_EVENING - currentHour <= 0 && currentMinute > 0 ||
            TIME_SIX_EVENING - currentHour <= 0 && currentHour != TIME_SIX_EVENING
        ) {
            icon.replace("d", "n")
        } else {
            icon
        }
    }
}