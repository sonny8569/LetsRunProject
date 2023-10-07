package com.sungil.controller.repository

import com.sungil.controller.interactor.GpsDataSource
import com.sungil.controller.interactor.RunningDataSource
import com.sungil.controller.interactor.UserInfoDBDataSource
import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData
import com.sungil.runningproejct_mvvm.domain.entity.WearRunData
import com.sungil.runningproejct_mvvm.domain.interactor.ControllerRepository
import com.sungil.runningproejct_mvvm.domain.interactor.RunningRepository
import com.sungil.runningproejct_mvvm.domain.interactor.UserInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ControllerRepositoryImpl @Inject constructor(
    private val gpsDataSource: GpsDataSource,
) : ControllerRepository {


    override suspend fun startGpsApi() {
        gpsDataSource.start()
    }

    override suspend fun stopGpsApi() {
        gpsDataSource.stop()
    }

    override fun getDistance(): Flow<Float> = gpsDataSource.distanceFlow()
}