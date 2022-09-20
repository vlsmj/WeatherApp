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

    private lateinit var user: User

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {
            user = it.getSerializableExtra(KEY_USER) as User
        }

        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
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
                                    }
                                )) {
                                CurrentWeatherScreen(
                                    it.arguments?.getString("username") ?: "",
                                    modifier = Modifier
                                )
                            }
                            composable(Screen.ListWeatherScreen.route) {
                                ListWeatherScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}