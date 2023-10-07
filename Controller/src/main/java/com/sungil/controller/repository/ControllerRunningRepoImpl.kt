package com.sungil.controller.repository

import com.sungil.controller.interactor.RunningDataSource
import com.sungil.runningproejct_mvvm.domain.entity.WearRunData
import com.sungil.runningproejct_mvvm.domain.interactor.RunningRepository
import javax.inject.Inject

class ControllerRunningRepoImpl @Inject constructor(private val runningDataSource: RunningDataSource) :
    RunningRepository {
    override fun insert(data: WearRunData) {
        runningDataSource.insert(data)
    }

    override fun update(data: WearRunData) {
        runningDataSource.update(data)
    }

    override fun getRunningData(): WearRunData? = runningDataSource.getRunningData()

}