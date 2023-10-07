package com.sungil.device.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sungil.database.entity.UserInfoDBM
import com.sungil.database.entity.WearRunDataDBM
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