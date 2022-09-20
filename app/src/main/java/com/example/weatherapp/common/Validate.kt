package com.example.weatherapp.common

object Validate {

    fun usernameAndPassword(username: String?, password: String?): String {
        if (username?.isBlank() == true) {
            return "Please enter your username."
        } else if (password?.isBlank() == true) {
            return "Please enter your password."
        }

        return ""
    }
}