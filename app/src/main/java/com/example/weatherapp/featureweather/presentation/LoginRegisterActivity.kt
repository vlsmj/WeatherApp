package com.example.weatherapp.featureweather.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.weatherapp.common.Constants.KEY_USER
import com.example.weatherapp.featureweather.presentation.screen.LoginRegisterScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginRegisterActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                LoginRegisterScreen(
                    modifier = Modifier
                        .fillMaxSize()
                ) { user ->
//                    openActivity(MainActivity::class.java, this) {
//                        Bundle().apply {
//                            putSerializable(KEY_USER, user)
//                        }
//                    }

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(KEY_USER, user)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}