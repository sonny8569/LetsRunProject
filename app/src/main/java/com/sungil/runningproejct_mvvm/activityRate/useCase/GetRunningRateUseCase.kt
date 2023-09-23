package com.sungil.runningproejct_mvvm.activityRate.useCase

import com.sungil.controller.repository.ControllerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRunningRateUseCase @Inject constructor(private val repository: ControllerRepository) {

    fun getRunningRate(): Flow<Float> {
        return repository.getDistance()
    }

}