package com.sungil.runningproejct_mvvm.appDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sungil.runningproejct_mvvm.dataObject.UserInfoDBM
import com.sungil.runningproejct_mvvm.dataObject.WearRunDataDBM
import javax.inject.Singleton

@Singleton
@Database(
    entities = [WearRunDataDBM::class, UserInfoDBM::class],
    version = 5
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wearRunningDao(): RunningDao
    abstract fun userInfoDao(): UserInfoDao
}