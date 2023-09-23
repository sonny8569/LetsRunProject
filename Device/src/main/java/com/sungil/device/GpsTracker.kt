package com.sungil.device

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import timber.log.Timber

class GpsTracker {
    companion object {
        private var instance: GpsTracker? = null

        fun getInstance(): GpsTracker {
            instance ?: synchronized(this) {
                instance ?: GpsTracker().also {
                    instance = it
                }
            }
            return instance!!
        }
    }

    private var locationClient: FusedLocationProviderClient? = null
    private val locationRequest = LocationRequest.create()
    private var totalDistance: Float = 0f
    val distanceFlow = MutableStateFlow(totalDistance)

    //위치 값 요청에 대한 갱신 정보를 받는 변수
    private var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)

            val locations = locationResult.locations
            if (locations.isNotEmpty()) {
                val location = locations.last()

                // 평균 속력을 구합니다 (단위: 킬로미터/시간)
                totalDistance = location.distanceTo(locationResult.lastLocation)
                Timber.d("totalDistance : $totalDistance")
                distanceFlow.value = totalDistance
            }
        }
    }

    @SuppressLint("MissingPermission")
    suspend fun startGpsApi() {
        locationClient =
            LocationServices.getFusedLocationProviderClient(DeviceMainApplication.appContext)
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
    }

    fun stopGpsApi() {
        locationClient?.removeLocationUpdates(locationCallback)
    }


}