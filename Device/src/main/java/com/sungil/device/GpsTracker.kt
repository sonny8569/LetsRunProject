package com.sungil.device

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.sungil.controller.interactor.GpsDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject


class GpsTracker @Inject constructor(
    @ApplicationContext private val context: Context,
) : GpsDataSource {
    private var locationClient: FusedLocationProviderClient? = null
    private val locationRequest = LocationRequest.create()
    private val _distanceFlow = MutableStateFlow(0f)
    private val distanceFlow: Flow<Float> get() = _distanceFlow

    //위치 값 요청에 대한 갱신 정보를 받는 변수
    private var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)

            val locations = locationResult.locations
            if (locations.isNotEmpty()) {
                val location = locations.last()

                // 평균 속력을 구합니다 (단위: 킬로미터/시간)
                Timber.d("totalDistance : $locationResult.lastLocation")
                _distanceFlow.value = location.distanceTo(locationResult.lastLocation)
            }
        }
    }

    @SuppressLint("MissingPermission")
    override suspend fun start() {
        locationClient =
            LocationServices.getFusedLocationProviderClient(context)
        locationClient!!.lastLocation.addOnSuccessListener { location: Location? ->
            Timber.e("LastLocation : ${location?.speed}")
        }
        locationRequest.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000
        }
        withContext(Dispatchers.Main) {
            Looper.myLooper()?.let { looper ->
                Timber.d("The Lopper is Null")
                return@withContext
            }
            locationClient?.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()!!
            )
        }
        locationClient?.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()!!
        )
    }

    override suspend fun stop() {
        locationClient?.removeLocationUpdates(locationCallback)
    }

    override fun distanceFlow(): Flow<Float> = distanceFlow
}