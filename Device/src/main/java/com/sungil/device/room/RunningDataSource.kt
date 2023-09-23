package com.sungil.device.room

import androidx.lifecycle.LiveData
import com.sungil.controller.interactor.RunningDataSource
import com.sungil.device.entity.toDBM
import com.sungil.device.entity.toDomainModel
import com.sungil.runningproejct_mvvm.domain.entity.WearRunData

class RunningDataSource(
    private val dao: RunningDao
) : RunningDataSource {
    override fun getRunningDataLiveData(): LiveData<WearRunData?> {
        return dao.getRunningDataLiveData()
    }

    override fun insert(data: WearRunData) {
        dao.insert(data.toDBM())
    }

    override fun update(data: WearRunData) {
        dao.update(data.toDBM())
    }

    override fun delete(data: WearRunData) {
        dao.delete(data.toDBM())
    }

    override fun getRunningData(): WearRunData? {
        return dao.getRunningData()?.toDomainModel()
    }
}