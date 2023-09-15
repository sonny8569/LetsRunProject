package com.sungil.runningproejct_mvvm.activityRate.useCase

import com.sungil.runningproejct_mvvm.activityRate.dataObject.GpsData
import com.sungil.runningproejct_mvvm.activityRate.repository.RateRepository
import javax.inject.Inject

class GetRunningRateUseCase @Inject constructor(private val repository: RateRepository) {

    fun getRunningRate() {
        repository.getLocationData()
    }

}