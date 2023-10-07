package com.sungil.runningproejct_mvvm.domain.interactor

import com.sungil.runningproejct_mvvm.domain.entity.WearRunData

interface RunningRepository {

    fun getRunningData(): WearRunData?

    fun insert(data: WearRunData)

    fun update(data: WearRunData)
}