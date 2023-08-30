package com.sungil.runningproejct_mvvm.repository.mainRepositoryImpl

import com.sungil.runningproejct_mvvm.appDatabase.RunningDao
import com.sungil.runningproejct_mvvm.appDatabase.UserInfoDao
import com.sungil.runningproejct_mvvm.repository.MainRepository
import com.sungil.runningproejct_mvvm.useCase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.content.Context
import com.sungil.runningproejct_mvvm.appDatabase.RunningDao
import com.sungil.runningproejct_mvvm.appDatabase.UserInfoDao
import com.sungil.runningproejct_mvvm.repository.LoginRepository
import com.sungil.runningproejct_mvvm.repository.loginImpl.LoginRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
object MainRepoModule {
    @Provides
    @Singleton
    fun providedMainRepository(
        runningDao : RunningDao,
        userDao : UserInfoDao,
        useCase : UseCase
    ): MainRepository  {
        return MainRepositoryImpl(runningDao , userDao ,useCase )
    }


        @ApplicationContext context: Context,
        runningDao : RunningDao
    ): MainRepositoryImpl {
        return MainRepositoryImpl(context , runningDao)
    }
}