package com.sungil.runningproejct_mvvm.domain.usecase.rate

import com.sungil.runningproejct_mvvm.domain.interactor.ControllerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRunningRateUseCase @Inject constructor(private val repository: ControllerRepository) {

    fun getRunningRate(): Flow<Float> {
        return repository.getDistance()
    }

}