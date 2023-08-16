package com.sungil.runningproejct_mvvm.repository.mainRepositoryImpl

import android.content.Context
import androidx.lifecycle.LiveData
import com.sungil.runningproejct_mvvm.appDatabase.AppDatabase
import com.sungil.runningproejct_mvvm.appDatabase.RunningDao
import com.sungil.runningproejct_mvvm.`object`.WearRunDataDBM
import com.sungil.runningproejct_mvvm.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val context : Context, private val wearRoomData : RunningDao) : MainRepository {
    //repository
    override fun getRunningRoomDB(): LiveData<WearRunDataDBM?> {
        return wearRoomData.getRunningData()
    }


}