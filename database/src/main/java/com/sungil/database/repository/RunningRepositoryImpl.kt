package com.sungil.database.repository

import com.sungil.database.entity.toDBM
import com.sungil.database.entity.toDomainModel
import com.sungil.device.room.RunningDao
import com.sungil.runningproejct_mvvm.domain.entity.WearRunData
import com.sungil.runningproejct_mvvm.domain.interactor.RunningRepository
import javax.inject.Inject

class RunningRepositoryImpl @Inject constructor(
    private val runningDao: RunningDao,
) : RunningRepository {
    override fun getRunningData(): WearRunData? {
        return runningDao.getRunningData()?.toDomainModel()
    }

    override fun insert(data: WearRunData) {
        runningDao.insert(data.toDBM())
    }

    override fun update(data: WearRunData) {
        runningDao.update(data.toDBM())
    }
}