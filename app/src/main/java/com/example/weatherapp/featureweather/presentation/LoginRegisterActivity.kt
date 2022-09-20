package com.example.weatherapp.featureweather.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.weatherapp.common.Constants.KEY_USER
import com.example.weatherapp.common.PrefManager
import com.example.weatherapp.featureweather.domain.model.User
import com.example.weatherapp.featureweather.presentation.screen.LoginRegisterScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginRegisterActivity : ComponentActivity() {

    @Inject
    lateinit var prefManager: PrefManager

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefManager.isInitialFetch = true

        setContent {
            WeatherAppTheme {
                LoginRegisterScreen(
                    modifier = Modifier
                        .fillMaxSize()
                ) { user ->
                    redirectToPermission(user)
                }
            }
        }
    }

    private fun redirectToPermission(user: User) {
        val intent = Intent(this, PermissionActivity::class.java)
        intent.putExtra(KEY_USER, user)
        startActivity(intent)
        finish()
    }
}