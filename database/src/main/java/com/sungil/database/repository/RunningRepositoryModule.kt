package com.sungil.database.repository

import com.sungil.controller.interactor.RunningDataSource
import com.sungil.device.room.RunningDao
import com.sungil.runningproejct_mvvm.domain.interactor.RunningRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RunningRepositoryModule {

    @Provides
    fun providesRunningRepository(dao: RunningDao): RunningDataSource {
        return RunningRepositoryImpl(dao)
    }

}