package com.sungil.controller.repository

import com.sungil.controller.interactor.GpsDataSource
import com.sungil.controller.interactor.RunningDataSource
import com.sungil.controller.interactor.UserInfoDBDataSource
import com.sungil.runningproejct_mvvm.domain.interactor.ControllerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ControllerRepoModule {


    @Provides
    fun providesControllerRepo(
        gpsDataSource: GpsDataSource,
    ): ControllerRepository {
        return ControllerRepositoryImpl(gpsDataSource)
    }
}