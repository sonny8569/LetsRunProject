package com.sungil.runningproejct_mvvm.activityRate.useCase

import com.sungil.controller.repository.ControllerRepository
import javax.inject.Inject

class StopRunningRateUseCase @Inject constructor(private val repository: ControllerRepository) {
    suspend fun stopRunningRate() {
        repository.stopGpsApi()
    }
}