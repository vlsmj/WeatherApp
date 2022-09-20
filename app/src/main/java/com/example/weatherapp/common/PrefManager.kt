package com.example.weatherapp.common

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PrefManager(
    private val application: Application,
) {

    companion object {
        private const val PREF_SHARED = "PREF_SHARED"

        private const val PREF_INITIAL_FETCH = "PREF_INITIAL_FETCH"
    }

    private fun getSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences(PREF_SHARED, Context.MODE_PRIVATE)
    }

    var isInitialFetch: Boolean
        get() = getSharedPreferences().getBoolean(PREF_INITIAL_FETCH, true)
        set(value) {
            getSharedPreferences().edit {
                putBoolean(PREF_INITIAL_FETCH, value)
            }
        }
}