@file:Suppress("UNUSED_EXPRESSION")
@file:OptIn(ExperimentalPermissionsApi::class)

package com.CyberDunkers.Sla7ly.presentation.clint.home.MapScreen

import LocationDialog
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.location.LocationManagerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.isPermanentlyDenied
import com.CyberDunkers.Sla7ly.common.openSettingForEnable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import java.util.Locale


@Composable
fun MapsScreen(
    address: MutableState<String>,
    geoPoint: MutableState<GeoPoint>,
    closeMap: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },

    ) {
    val context = LocalContext.current
    val geocoder = Geocoder(context, Locale.getDefault())

    val cameraState = rememberCameraState {
        GeoPoint(26.8206, 30.8025)
        zoom = 15.0 // optional, default is 5.0
    }
    val GrantedPermission = remember {
        mutableStateOf(false)
    }
    val shouldShowRationale = remember {
        mutableStateOf(false)
    }
    val isPermanentlyDenied = remember {
        mutableStateOf(false)
    }
    val currentLocation = remember {
        mutableStateOf("not available please ensure that you give the ap the location access")
    }

    val mapProperties = remember {
        mutableStateOf(DefaultMapProperties)
    }
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    val scope = rememberCoroutineScope()

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
            }
        }
    )
    permissionsState.permissions.forEach { perm ->
        when (perm.permission) {
            Manifest.permission.ACCESS_FINE_LOCATION -> {
                when {
                    perm.hasPermission -> {
                        getCurrentLocation(context) { Lat, Long ->
                            cameraState.zoom = 18.0
                            cameraState.geoPoint = GeoPoint(Lat, Long)
                            val addresses = geocoder.getFromLocation(
                                cameraState.geoPoint.latitude,
                                cameraState.geoPoint.longitude,
                                5
                            )
                            val address: Address = addresses!![0]
                            currentLocation.value = address.getAddressLine(0)

                            GrantedPermission.value = true


                        }
                    }

                    perm.shouldShowRationale -> {
                        currentLocation.value = "Location permission is needed" +
                                "to access the camera"
                    }

                    perm.isPermanentlyDenied() -> {
                        currentLocation.value = "Location permission is needed" +
                                "go for setting and enable it "

                    }
                }
            }
        }
    }
    if (!isLocationEnabled(context)){
        currentLocation.value = "you must enable gps to get your location please enable it amd try again "
        SideEffect {
            permissionsState.launchMultiplePermissionRequest()
        }

    }



// setup mapProperties in side effect
    Box(Modifier.fillMaxSize()) {

        SideEffect {
            mapProperties.value = mapProperties
                .value.copy(isTilesScaledToDpi = true)
                .copy(tileSources = TileSourceFactory.DEFAULT_TILE_SOURCE)
                .copy(isEnableRotationGesture = false)
                .copy(zoomButtonVisibility = ZoomButtonVisibility.NEVER)
                .copy(minZoomLevel = -1000.0)
                .copy(maxZoomLevel = 70.0)
        }

        if (GrantedPermission.value) {
            OpenStreetMap(
                modifier = Modifier.fillMaxSize(),
                cameraState = cameraState,
                properties = mapProperties.value
            ) {
                val markerState = rememberMarkerState(
                    geoPoint = cameraState.geoPoint
                )
                Marker(
                    state = markerState // add marker state
                    , icon = context.getDrawable(R.drawable.baseline_location_on_24), visible = true
                )

            }

        }
        // add node
        LocationDialog(
            currentLocation.value
        ) {
            address.value = currentLocation.value
            geoPoint.value = cameraState.geoPoint
            closeMap.value = true

        }
        if (shouldShowRationale.value) {
            PermissionDialog(
                isPermanentlyDeclined = false,
                onDismiss = { /*TODO*/ },
                onOkClick = {
                    permissionsState.launchMultiplePermissionRequest()
                    shouldShowRationale.value = false
                },
                onGoToAppSettingsClick = { /*TODO*/ })
        }

        if (isPermanentlyDenied.value){
            PermissionDialog(
                isPermanentlyDeclined = true,
                onDismiss = { /*TODO*/ },
                onOkClick = { /*TODO*/ },
                onGoToAppSettingsClick = {
                    context.openSettingForEnable()
                    isPermanentlyDenied.value = false
                })
        }

    }
}


@SuppressLint("MissingPermission")
private fun getCurrentLocation(
    context: Context,
    callback: (Double, Double) -> Unit,
) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location ->
            if (location != null) {
                val lat = location.latitude
                val long = location.longitude
                callback(lat, long)

            }
        }
        .addOnFailureListener { exception ->
            exception.printStackTrace()
        }

}

private fun isLocationEnabled(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return LocationManagerCompat.isLocationEnabled(locationManager)
}

