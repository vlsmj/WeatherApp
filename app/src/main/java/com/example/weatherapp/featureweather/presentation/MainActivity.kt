package com.example.weatherapp.featureweather.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.R
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
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Weather",
                                    route = Screen.CurrentWeatherScreen.route,
                                    icon = ImageVector.vectorResource(id = R.drawable.ic_sun)
                                ),
                                BottomNavItem(
                                    name = "History",
                                    route = Screen.ListWeatherScreen.route,
                                    icon = ImageVector.vectorResource(id = R.drawable.ic_history)
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
                                    .fillMaxSize()
                                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 50.dp)
                            )
                        }
                        composable(Screen.ListWeatherScreen.route) {
                            ListWeatherScreen(modifier = Modifier.padding(bottom = 50.dp))
                        }
                    }
                }
            }
        }
    }
}