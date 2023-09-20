package com.sungil.runningproejct_mvvm.activityRate.useCase

import com.sungil.controller.repository.ControllerRepository
import javax.inject.Inject

class StartRunningRateUseCase @Inject constructor(private val repository: ControllerRepository) {
    suspend fun startRunningRate() {
        repository.startGpsApi()
    }
}