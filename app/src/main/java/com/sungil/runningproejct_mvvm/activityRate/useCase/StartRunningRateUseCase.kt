package com.sungil.runningproejct_mvvm.activityRate.useCase

import com.sungil.runningproejct_mvvm.activityRate.repository.RateRepository
import javax.inject.Inject

class StartRunningRateUseCase @Inject constructor(private val repository: RateRepository) {
    suspend fun startRunningRate() {
        repository.startLocationRate()
    }
}