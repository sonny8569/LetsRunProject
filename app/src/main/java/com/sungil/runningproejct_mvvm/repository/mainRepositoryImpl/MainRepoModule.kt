package com.sungil.runningproejct_mvvm.repository.mainRepositoryImpl

import com.sungil.runningproejct_mvvm.appDatabase.RunningDao
import com.sungil.runningproejct_mvvm.appDatabase.UserInfoDao
import com.sungil.runningproejct_mvvm.repository.MainRepository
import com.sungil.runningproejct_mvvm.useCase.GetUnFollowerUseCase
import com.sungil.runningproejct_mvvm.useCase.GetFollowerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//MainRepo Module
@Module
@InstallIn(SingletonComponent::class)
object MainRepoModule {
    @Provides
    @Singleton
    fun providedMainRepository(
        runningDao: RunningDao,
        userDao: UserInfoDao,
        getFollowerUseCase: GetFollowerUseCase,
        unFollowerUseCase: GetUnFollowerUseCase,
    ): MainRepository {
        return MainRepositoryImpl(runningDao, userDao, getFollowerUseCase, unFollowerUseCase)
    }


}