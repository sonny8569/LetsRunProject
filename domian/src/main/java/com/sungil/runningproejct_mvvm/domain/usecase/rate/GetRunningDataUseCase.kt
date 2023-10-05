package com.sungil.runningproejct_mvvm.domain.usecase.rate

import com.sungil.runningproejct_mvvm.domain.entity.WearRunData
import com.sungil.runningproejct_mvvm.domain.interactor.ControllerRepository
import com.sungil.runningproejct_mvvm.domain.interactor.RunningRepository
import javax.inject.Inject

class GetRunningDataUseCase @Inject constructor(private val runningRepo: ControllerRepository) {

    fun getRunningData(): WearRunData? {
        return runningRepo.getRunningData()
    }
}