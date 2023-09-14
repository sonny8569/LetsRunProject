package com.sungil.runningproejct_mvvm.activityRate.useCase

import androidx.lifecycle.LiveData
import com.sungil.runningproejct_mvvm.activityRate.dataObject.GpsData
import com.sungil.runningproejct_mvvm.activityRate.repository.RateRepository
import java.lang.Math.atan2
import java.lang.Math.cos
import java.lang.Math.sin
import java.lang.Math.sqrt
import javax.inject.Inject

class GetKmRateUseCase @Inject constructor(private val repository: RateRepository) {

    private var gpsData: GpsData? = null
    fun getRunningRate() {
        repository.getLocationData()
    }

    fun stopRunningRate() {
        repository.stopLocationRate()
    }

    fun startRunningRate() {
        repository.startLocationRate()
    }


    private fun calculateDistance(beforeData: GpsData, newData: GpsData): Double {
        val earthRadius = 6371000.0 // 지구의 반지름 (미터)
        val latitude = Math.toRadians(newData.latitude - beforeData.latitude)
        val longitude = Math.toRadians(newData.longitude - beforeData.longitude)

        val a = kotlin.math.sin(latitude / 2) * kotlin.math.sin(latitude / 2) +
                kotlin.math.cos(Math.toRadians(beforeData.latitude)) * kotlin.math.cos(
            Math.toRadians(
                newData.latitude
            )
        ) * kotlin.math.sin(longitude / 2) * kotlin.math.sin(longitude / 2)
        val c = 2 * kotlin.math.atan2(kotlin.math.sqrt(a), kotlin.math.sqrt(1 - a))
        return earthRadius * c
    }
}