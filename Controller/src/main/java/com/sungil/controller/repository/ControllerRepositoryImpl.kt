package com.sungil.controller.repository

import com.sungil.device.GpsTracker
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ControllerRepositoryImpl() :
    ControllerRepository {


    override suspend fun startGpsApi() {
        GpsTracker.getInstance().startGpsApi()
    }

    override suspend fun stopGpsApi() {
        GpsTracker.getInstance().stopGpsApi()
    }

    override fun getDistance(): Flow<Float> = GpsTracker.getInstance().distanceFlow

}