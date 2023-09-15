package com.sungil.runningproejct_mvvm.activityRate.useCase

import com.sungil.runningproejct_mvvm.activityRate.repository.RateRepository
import javax.inject.Inject

class StopRunningRateUseCase @Inject constructor(private val repository: RateRepository) {
    fun stopRunningRate() {
        repository.stopLocationRate()
    }
}