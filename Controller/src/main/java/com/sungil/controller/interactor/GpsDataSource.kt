package com.sungil.controller.interactor

import kotlinx.coroutines.flow.Flow

interface GpsDataSource {
    suspend fun start()
    suspend fun stop()
    fun getDistanceFlow(): Flow<Float>
}