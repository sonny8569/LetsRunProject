package com.sungil.runningproejct_mvvm.domain.interactor

import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData
import com.sungil.runningproejct_mvvm.domain.entity.WearRunData
import kotlinx.coroutines.flow.Flow

interface ControllerRepository {

    suspend fun startGpsApi()

    suspend fun stopGpsApi()

    fun getDistance(): Flow<Float>
}