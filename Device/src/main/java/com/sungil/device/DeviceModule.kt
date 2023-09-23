package com.sungil.device

import android.content.Context
import com.sungil.controller.interactor.GpsDataSource
import com.sungil.controller.interactor.RunningDataSource
import com.sungil.device.room.RunningDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DeviceModule {

    @Provides
    fun providesGpsDataSource(@ApplicationContext context: Context): GpsDataSource {
        return GpsTracker.getInstance(context)
    }

    @Provides
    fun providesRunningDataSource(dao : RunningDao): RunningDataSource {
        return com.sungil.device.room.RunningDataSource(dao)
    }
}