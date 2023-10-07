package com.sungil.runningproejct_mvvm.activityRate.useCase

import com.sungil.runningproejct_mvvm.domain.interactor.ControllerRepository
import com.sungil.runningproejct_mvvm.domain.usecase.rate.GetRunningRateUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRunningRateUseCase @Inject constructor(private val repository: GetRunningRateUseCase) {

    fun getRunningRate(): Flow<Float> {
        return repository.getRunningRate()
    }

}