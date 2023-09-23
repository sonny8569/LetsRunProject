package com.sungil.controller.repository

import kotlinx.coroutines.flow.Flow

interface ControllerRepository {

    suspend fun startGpsApi()

    suspend fun stopGpsApi()

    fun getDistance(): Flow<Float>
}