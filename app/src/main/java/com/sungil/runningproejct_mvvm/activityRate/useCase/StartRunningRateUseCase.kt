package com.sungil.runningproejct_mvvm.activityRate.useCase

import com.sungil.runningproejct_mvvm.domain.interactor.ControllerRepository
import com.sungil.runningproejct_mvvm.domain.usecase.rate.StartRunningRateUseCase
import javax.inject.Inject

class StartRunningRateUseCase @Inject constructor(private val repository: StartRunningRateUseCase) {
    suspend fun startRunningRate() {
        repository.startRunningRate()
    }
}