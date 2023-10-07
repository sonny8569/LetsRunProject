package com.sungil.runningproejct_mvvm.domain.usecase.rate

import com.sungil.runningproejct_mvvm.domain.interactor.ControllerRepository
import javax.inject.Inject

class StartRunningRateUseCase @Inject constructor(private val repository: ControllerRepository) {
    suspend fun startRunningRate() {
        repository.startGpsApi()
    }
}