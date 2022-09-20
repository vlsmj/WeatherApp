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
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
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

                permissionsState.permissions.forEach { perm ->
                    when (perm.permission) {
                        Manifest.permission.ACCESS_FINE_LOCATION -> {
                            when {
                                perm.hasPermission -> {
                                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0F, this)
                                }
                                perm.shouldShowRationale -> {
                                    Text(text = "Location permission is needed to fetch the data.")
                                }
                                !perm.shouldShowRationale && !perm.hasPermission -> {
                                    Text(text = "Location permission was permanently denied."
                                            + "You can enable it in the app settings.")
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
            val address: String = addresses[0].getAddressLine(0)

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

            }
        builder.create().show()
    }

    override fun onProviderEnabled(provider: String) {

    }

    @Deprecated("Deprecated in Java")
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }
}