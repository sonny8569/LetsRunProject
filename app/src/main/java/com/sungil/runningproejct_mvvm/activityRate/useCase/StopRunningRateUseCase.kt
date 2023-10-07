package com.sungil.runningproejct_mvvm.activityRate.useCase

import com.sungil.runningproejct_mvvm.domain.usecase.rate.StopRunningRateUseCase

import javax.inject.Inject

class StopRunningRateUseCase @Inject constructor(
    private val repository: StopRunningRateUseCase,
) {
    suspend fun stopRunningRate(): StopRunningRateUseCase.Result {
        return repository.stopRunningRate()
    }

    fun saveRunningDistance(distance: String) {
        repository.saveRunningRate(distance)
    }

}