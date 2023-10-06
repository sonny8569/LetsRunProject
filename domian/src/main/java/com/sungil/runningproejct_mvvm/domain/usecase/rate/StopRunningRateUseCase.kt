package com.sungil.runningproejct_mvvm.domain.usecase.rate

import com.sungil.runningproejct_mvvm.domain.entity.WearRunData
import com.sungil.runningproejct_mvvm.domain.interactor.ControllerRepository
import com.sungil.runningproejct_mvvm.domain.interactor.RunningRepository
import com.sungil.runningproejct_mvvm.domain.usecase.UseCase
import javax.inject.Inject

class StopRunningRateUseCase @Inject constructor(
    private val repository: ControllerRepository,
    private val runningRepo: RunningRepository,
) {
    suspend fun stopRunningRate(): StopRunningRateUseCase.Result {
        repository.stopGpsApi()
        return StopRunningRateUseCase.Result.Success
    }

    fun saveRunningRate(distance: String) {
        val beforeData = runningRepo.getRunningData()
        if (beforeData == null) {
            runningRepo.insert(WearRunData(0, distance))
            return
        }
        runningRepo.update(WearRunData(0, distance))
    }


    data class Param(
        val distance: String,
    ) : UseCase.Param

    sealed interface Result : UseCase.Result {
        object Success : StopRunningRateUseCase.Result
        data class Fail(val message: String) : StopRunningRateUseCase.Result
    }
}