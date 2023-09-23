package com.sungil.controller.interactor

import androidx.lifecycle.LiveData
import com.sungil.runningproejct_mvvm.domain.entity.WearRunData

interface RunningDataSource {
    fun getRunningDataLiveData () : LiveData<WearRunData?>

    fun insert(data : WearRunData)

    fun update(data : WearRunData)

    fun delete(data:  WearRunData)

    fun getRunningData () : WearRunData?
}