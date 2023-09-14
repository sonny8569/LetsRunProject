package com.sungil.runningproejct_mvvm.activityRate.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sungil.runningproejct_mvvm.activityRate.GpsTracker
import com.sungil.runningproejct_mvvm.appDatabase.RunningDao
import com.sungil.runningproejct_mvvm.dataObject.WearRunDataDBM
import javax.inject.Inject

class RateRepositoryImpl @Inject constructor(
    private val gpsTracker: GpsTracker,
    private val runningDao: RunningDao,
) : RateRepository {

    override fun getLocationData() {
        gpsTracker.setTotalDistanceCallback {
            saveRunningData(it)
        }
    }

    override fun stopLocationRate() {
        gpsTracker.stopLocationUpdate()
    }

    override fun startLocationRate() {
        gpsTracker.getLastLocation()
    }

    private fun saveRunningData(data: Float) {
        val databaseDao = runningDao.getRunningData()
        if (databaseDao == null) {
            runningDao.insert(WearRunDataDBM(data.toString()))
            return
        }
        runningDao.update(WearRunDataDBM(data.toString()))
    }

}