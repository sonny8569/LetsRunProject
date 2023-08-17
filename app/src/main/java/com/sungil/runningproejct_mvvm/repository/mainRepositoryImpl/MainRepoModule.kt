package com.sungil.runningproejct_mvvm.repository.mainRepositoryImpl

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

//MainRepo Module
@Module
@InstallIn(SingletonComponent :: class)
object MainRepoModule {
    @Provides
    @Singleton
    fun providedMainRepository(
        @ApplicationContext context: Context,
        runningDao : RunningDao
    ): MainRepositoryImpl {
        return MainRepositoryImpl(context , runningDao)
    }

}