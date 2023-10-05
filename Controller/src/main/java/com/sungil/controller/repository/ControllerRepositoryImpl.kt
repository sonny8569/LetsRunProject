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
    private val userInfoDBDataSource: UserInfoDBDataSource,
    private val runningDataSource: RunningDataSource,
) : ControllerRepository {


    override suspend fun startGpsApi() {
        gpsDataSource.start()
    }

    override suspend fun stopGpsApi() {
        gpsDataSource.stop()
    }

    override fun saveUserInfo(data: UserInfoData) {
        userInfoDBDataSource.insertUserInfo(data)
    }

    override fun updateUserInfo(data: UserInfoData) {
        userInfoDBDataSource.updateUserInfo(data)
    }

    override fun deleteUserInfo(data: UserInfoData) {
        userInfoDBDataSource.deleteUserInfo(data)
    }

    override fun insert(data: WearRunData) {
        runningDataSource.insert(data)
    }

    override fun update(data: WearRunData) {
        runningDataSource.update(data)
    }

    override fun getRunningData(): WearRunData? = runningDataSource.getRunningData()
    override fun getDistance(): Flow<Float> = gpsDataSource.distanceFlow()
    override fun getUserInfo(): UserInfoData? = userInfoDBDataSource.getUserInfo()
}