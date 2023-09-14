package com.sungil.runningproejct_mvvm.activityRate.repository

import com.sungil.runningproejct_mvvm.activityRate.GpsTracker
import com.sungil.runningproejct_mvvm.appDatabase.RunningDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RateRepositoryImplModule {

    @Provides
    @Singleton
    fun providedRateRepository(
        gpsTracker: GpsTracker,
        runningDao : RunningDao
    ): RateRepository {
        return RateRepositoryImpl(gpsTracker , runningDao)
    }
}