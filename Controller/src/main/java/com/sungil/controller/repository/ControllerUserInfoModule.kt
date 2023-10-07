package com.sungil.controller.repository

import com.sungil.controller.interactor.UserInfoDBDataSource
import com.sungil.runningproejct_mvvm.domain.interactor.UserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ControllerUserInfoModule {

    @Provides
    fun providesControllerUserInfoRepo(
        userinfoDBDataSource: UserInfoDBDataSource,
    ): UserInfoRepository {
        return ControllerUserInfoRepoImpl(userinfoDBDataSource)
    }
}