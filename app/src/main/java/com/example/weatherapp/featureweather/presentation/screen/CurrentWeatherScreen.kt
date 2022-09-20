package com.example.weatherapp.featureweather.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherapp.R
import com.example.weatherapp.featureweather.presentation.viewmodel.WeatherViewModel

@Composable
fun CurrentWeatherScreen(
    username: String,
    latitude: String,
    longitude: String,
    address: String,
    modifier: Modifier,
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    val state = viewModel.currentWeatherState.value

    val mUsername by remember {
        mutableStateOf(username)
    }

    val mLatitude by remember {
        mutableStateOf(latitude)
    }

    val mLongitude by remember {
        mutableStateOf(longitude)
    }

    val mAddress by remember {
        mutableStateOf(address)
    }

    LaunchedEffect(Unit) {
        viewModel.getCurrentWeather(mLatitude, mLongitude, mAddress)
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.primary) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            state.data?.let { weather ->
                Text(
                    text = "Hello, $mUsername!",
                    fontSize = 32.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = weather.location ?: "",
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = weather.temperatureInCelsius,
                    fontSize = 72.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(weather.icon)
                            .build(),
                        contentDescription = "icon"
                    )

                    Text(
                        text = "${weather.condition} (${weather.description})",
                        fontSize = 14.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.ic_sunset),
                            contentDescription = "sunset",
                            modifier = Modifier.size(24.dp))

                        Text(
                            text = weather.sunsetTime,
                            fontSize = 12.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.ic_sunrise),
                            contentDescription = "sunrise",
                            modifier = Modifier.size(24.dp))

                        Text(
                            text = weather.sunriseTime,
                            fontSize = 12.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}
















