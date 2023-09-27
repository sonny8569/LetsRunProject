package com.sungil.database.repository

import com.sungil.device.room.UserInfoDao
import com.sungil.runningproejct_mvvm.domain.interactor.UserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserInfoRepositoryModule {

    @Provides
    fun providesUserInfoRepository(dao: UserInfoDao): UserInfoRepository {
        return UserInfoRepositoryImpl(dao)
    }
}