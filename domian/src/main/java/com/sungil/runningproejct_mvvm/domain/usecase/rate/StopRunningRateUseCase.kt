package com.sungil.runningproejct_mvvm.domain.usecase.rate

import com.sungil.runningproejct_mvvm.domain.entity.WearRunData
import com.sungil.runningproejct_mvvm.domain.interactor.ControllerRepository
import com.sungil.runningproejct_mvvm.domain.interactor.RunningRepository
import com.sungil.runningproejct_mvvm.domain.usecase.UseCase
import javax.inject.Inject

class StopRunningRateUseCase @Inject constructor(
    private val repository: ControllerRepository,
    private val runningRepository: RunningRepository,
) {
    suspend fun stopRunningRate() {
        repository.stopGpsApi()
    }

    fun saveRunningRate(distance: String) {
        val beforeData = runningRepository.getRunningData()
        if (beforeData == null) {
            runningRepository.insert(WearRunData(0, distance))
            return
        }
        runningRepository.update(WearRunData(0, distance))
    }


    data class Param(
        val distance : String
    ) : UseCase.Param

    sealed interface Result : UseCase.Result {
        object Success : StopRunningRateUseCase.Result
        data class Fail(val message : String) : StopRunningRateUseCase.Result
    }
}