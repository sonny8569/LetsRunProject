package com.sungil.runningproejct_mvvm.appDatabase.module

import android.content.Context
import androidx.room.Room
import com.sungil.runningproejct_mvvm.appDatabase.AppDatabase
import com.sungil.runningproejct_mvvm.appDatabase.RunningDao
import com.sungil.runningproejct_mvvm.appDatabase.UserInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//Appdatabase Moudle
@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideRunningDao(appDatabase: AppDatabase): RunningDao {
        return appDatabase.wearRunningDao()
    }

    @Provides
    fun provideUserInfoDao(appDatabase: AppDatabase): UserInfoDao {
        return appDatabase.userInfoDao()
    }
}