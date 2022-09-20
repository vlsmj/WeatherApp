package com.example.weatherapp.featureweather.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.location.*
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.weatherapp.R
import com.example.weatherapp.common.Constants.KEY_ADDRESS
import com.example.weatherapp.common.Constants.KEY_LATITUDE
import com.example.weatherapp.common.Constants.KEY_LONGITUDE
import com.example.weatherapp.common.Constants.KEY_USER
import com.example.weatherapp.common.Constants.TAG_EXCEPTION
import com.example.weatherapp.featureweather.domain.model.User
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PermissionActivity : ComponentActivity(), LocationListener {

    private lateinit var locationManager: LocationManager

    private lateinit var user: User

    @OptIn(ExperimentalPermissionsApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {
            user = it.getSerializableExtra(KEY_USER) as User
        }

        locationManager = applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager

        setContent {
            WeatherAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.primary) {
                    val permissionsState = rememberMultiplePermissionsState(
                        permissions = listOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )

                    val lifecycleOwner = LocalLifecycleOwner.current
                    DisposableEffect(
                        key1 = lifecycleOwner,
                        effect = {
                            val observer = LifecycleEventObserver { _, event ->
                                if (event == Lifecycle.Event.ON_START) {
                                    permissionsState.launchMultiplePermissionRequest()
                                }
                            }
                            lifecycleOwner.lifecycle.addObserver(observer)

                            onDispose {
                                lifecycleOwner.lifecycle.removeObserver(observer)
                                locationManager.removeUpdates(this@PermissionActivity)
                            }
                        }
                    )

                    Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        permissionsState.permissions.forEach { perm ->
                            when (perm.permission) {
                                Manifest.permission.ACCESS_FINE_LOCATION -> {
                                    when {
                                        perm.hasPermission -> {
                                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0F, this@PermissionActivity)


                                            Image(painter = painterResource(id = R.drawable.ic_gps),
                                                contentDescription = "gps",
                                                modifier = Modifier.size(72.dp))

                                            Spacer(modifier = Modifier.height(16.dp))

                                            CircularProgressIndicator(
                                                modifier = Modifier.size(24.dp),
                                                strokeWidth = 2.dp,
                                                color = Color.White
                                            )

                                            Spacer(modifier = Modifier.height(8.dp))

                                            Text(
                                                text = getString(R.string.fetching_gps),
                                                fontSize = 12.sp,
                                                color = Color.White
                                            )
                                        }
                                        perm.shouldShowRationale -> {
                                            Text(
                                                modifier = Modifier.padding(horizontal = 32.dp),
                                                text = getString(R.string.location_permission_denied),
                                                fontSize = 12.sp,
                                                color = Color.White,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                        !perm.shouldShowRationale && !perm.hasPermission -> {
                                            Text(
                                                modifier = Modifier.padding(horizontal = 32.dp),
                                                text = getString(R.string.location_permission_denied_final),
                                                fontSize = 12.sp,
                                                color = Color.White,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onLocationChanged(location: Location) {
        try {
            val geocoder = Geocoder(this, Locale.getDefault())
            val geoLatitude = location.latitude
            val geoLongitude = location.longitude
            val addresses: List<Address> =
                geocoder.getFromLocation(geoLatitude, geoLongitude, 1)
            val address = "${addresses[0].locality}, ${addresses[0].countryName}"

            removeServices()

            redirectToMain(geoLatitude, geoLongitude, address)
        } catch (e: Exception) {
            e.localizedMessage?.let { Log.e(TAG_EXCEPTION, it) }
        }
    }

    private fun redirectToMain(latitude: Double, longitude: Double, address: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(KEY_USER, user)
            putExtras(Bundle().apply {
                putDouble(KEY_LATITUDE, latitude)
                putDouble(KEY_LONGITUDE, longitude)
                putString(KEY_ADDRESS, address)
            })
        }
        startActivity(intent)
        finish()
    }

    private fun removeServices() {
        locationManager.removeUpdates(this)
    }

    override fun onDestroy() {
        removeServices()
        super.onDestroy()
    }

    override fun onProviderDisabled(provider: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.dialog_message_location_disabled)
            .setPositiveButton(R.string.yes) { _, _ ->
                startActivity(Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .setNegativeButton(R.string.no) { _, _ ->
                val intent = Intent(this, LoginRegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        builder.create().show()
    }

    override fun onProviderEnabled(provider: String) {

    }

    @Deprecated("Deprecated in Java")
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }
}