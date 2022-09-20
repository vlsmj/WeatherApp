package com.example.weatherapp.featureweather.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.common.Constants.DEFAULT_LATITUDE
import com.example.weatherapp.common.Constants.DEFAULT_LONGITUDE
import com.example.weatherapp.common.Constants.KEY_ADDRESS
import com.example.weatherapp.common.Constants.KEY_LATITUDE
import com.example.weatherapp.common.Constants.KEY_LONGITUDE
import com.example.weatherapp.common.Constants.KEY_USER
import com.example.weatherapp.common.components.BottomNavItem
import com.example.weatherapp.common.components.BottomNavigationBar
import com.example.weatherapp.featureweather.domain.model.User
import com.example.weatherapp.featureweather.presentation.screen.CurrentWeatherScreen
import com.example.weatherapp.featureweather.presentation.screen.ListWeatherScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var latitude: Double = DEFAULT_LATITUDE
    private var longitude: Double = DEFAULT_LONGITUDE
    private var address: String = ""

    private lateinit var user: User

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let { intent ->
            user = intent.getSerializableExtra(KEY_USER) as User

            intent.extras?.let { bundle ->
                latitude = bundle.getDouble(KEY_LATITUDE, DEFAULT_LATITUDE)
                longitude = bundle.getDouble(KEY_LONGITUDE, DEFAULT_LONGITUDE)
                address = bundle.getString(KEY_ADDRESS, "")
            }
        }

        setContent {
            WeatherAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = listOf(
                                    BottomNavItem(
                                        name = "Weather",
                                        route = Screen.CurrentWeatherScreen.route,
                                        icon = Icons.Default.Home
                                    ),
                                    BottomNavItem(
                                        name = "List",
                                        route = Screen.ListWeatherScreen.route,
                                        icon = Icons.Default.Home
                                    ),
                                ),
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route)
                                }
                            )
                        }
                    ) {
                        NavHost(navController = navController, startDestination = Screen.CurrentWeatherScreen.route) {
                            composable(Screen.CurrentWeatherScreen.route,
                                arguments = listOf(
                                    navArgument(name = "username") {
                                        type = NavType.StringType
                                        defaultValue = user.username
                                    },
                                    navArgument(name = "lat") {
                                        type = NavType.StringType
                                        defaultValue = latitude.toString()
                                    },
                                    navArgument(name = "lon") {
                                        type = NavType.StringType
                                        defaultValue = longitude.toString()
                                    },
                                    navArgument(name = "address") {
                                        type = NavType.StringType
                                        defaultValue = address
                                    }
                                )) {
                                val username = it.arguments?.getString("username") ?: ""
                                val latitude = it.arguments?.getString("lat") ?: "$DEFAULT_LATITUDE"
                                val longitude = it.arguments?.getString("lon") ?: "$DEFAULT_LONGITUDE"
                                val address = it.arguments?.getString("address") ?: ""

                                CurrentWeatherScreen(
                                    username,
                                    latitude,
                                    longitude,
                                    address,
                                    modifier = Modifier
                                )
                            }
                            composable(Screen.ListWeatherScreen.route) {
                                ListWeatherScreen(modifier = Modifier)
                            }
                        }
                    }
                }
            }
        }
    }
}