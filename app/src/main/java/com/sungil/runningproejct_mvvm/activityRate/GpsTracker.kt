package com.sungil.runningproejct_mvvm.activityRate

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.sungil.runningproejct_mvvm.MainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class GpsTracker @Inject constructor() {
    private var mLocationClient: FusedLocationProviderClient? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val locationRequest = LocationRequest.create()
    private var totalDistance: Float = 0f

    private var callback: ((Float) -> Unit)? = null
    fun setTotalDistanceCallback(callback: (Float) -> Unit) {
        this.callback = callback
    }

    //위치 값 요청에 대한 갱신 정보를 받는 변수
    private var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)

            val locations = locationResult.locations
            if (locations.isNotEmpty()) {
                val location = locations.last()

                // 평균 속력을 구합니다 (단위: 킬로미터/시간)
                totalDistance = location.distanceTo(locationResult.lastLocation)
                callback?.invoke(totalDistance)
                Timber.d("totalDistance : $totalDistance")
            }
        }
    }

    @SuppressLint("MissingPermission")
    suspend fun getLastLocation() {
        mLocationClient =
            LocationServices.getFusedLocationProviderClient(MainApplication.appContext)
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(MainApplication.appContext)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            Timber.e("LastLocation : ${location?.speed}")
        }
        locationRequest.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000
        }

        withContext(Dispatchers.Main) {
            if (ActivityCompat.checkSelfPermission(
                    MainApplication.appContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    MainApplication.appContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Timber.e("The Permission is Not Granted")
                return@withContext
            }
            if(Looper.myLooper() == null){
                Timber.d("The Lopper is Null")
                return@withContext
            }
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper()!!)
        }
    }

    fun stopLocationUpdate() {
        mLocationClient?.removeLocationUpdates(locationCallback)
    }


}