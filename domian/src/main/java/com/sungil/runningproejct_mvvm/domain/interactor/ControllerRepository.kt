package com.sungil.runningproejct_mvvm.domain.interactor

import kotlinx.coroutines.flow.Flow

interface ControllerRepository {

    suspend fun startGpsApi()

    suspend fun stopGpsApi()

    fun getDistance(): Flow<Float>
}