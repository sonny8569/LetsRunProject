package com.sungil.runningproejct_mvvm.repository.mainRepositoryImpl

import com.sungil.device.room.RunningDao
import com.sungil.runningproejct_mvvm.domain.usecase.rate.GetRunningDataUseCase
import com.sungil.runningproejct_mvvm.domain.usecase.rate.GetRunningRateUseCase
import com.sungil.runningproejct_mvvm.domain.usecase.user.GetUserInfoUseCase
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
        getUserInfoUseCase: GetUserInfoUseCase,
        getFollowerUseCase: GetFollowerUseCase,
        unFollowerUseCase: GetUnFollowerUseCase,
        getRunningDataUseCase: GetRunningDataUseCase,
    ): MainRepository {
        return MainRepositoryImpl(
            getUserInfoUseCase,
            getFollowerUseCase,
            unFollowerUseCase,
            getRunningDataUseCase
        )
    }


}