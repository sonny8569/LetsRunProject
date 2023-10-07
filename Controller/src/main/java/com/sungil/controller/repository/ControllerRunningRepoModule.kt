package com.sungil.controller.repository

import com.sungil.controller.interactor.RunningDataSource
import com.sungil.runningproejct_mvvm.domain.interactor.RunningRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ControllerRunningRepoModule {

    @Provides
    fun providesControllerRunningRepo(runningDataSource: RunningDataSource): RunningRepository {
        return ControllerRunningRepoImpl(runningDataSource)
    }
}