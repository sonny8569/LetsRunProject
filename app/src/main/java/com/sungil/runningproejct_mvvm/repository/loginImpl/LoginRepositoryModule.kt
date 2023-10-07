package com.sungil.runningproejct_mvvm.repository.loginImpl

import android.content.Context
import com.sungil.device.room.UserInfoDao
import com.sungil.runningproejct_mvvm.domain.usecase.user.DeleteUserInfoUseCase
import com.sungil.runningproejct_mvvm.domain.usecase.user.GetUserInfoUseCase
import com.sungil.runningproejct_mvvm.domain.usecase.user.SaveUserinfoUseCase
import com.sungil.runningproejct_mvvm.domain.usecase.user.UpdateUserInfoUseCase
import com.sungil.runningproejct_mvvm.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Repo Moudle
@Module
@InstallIn(SingletonComponent::class)
object LoginRepositoryModule {
    @Provides
    @Singleton
    fun provideLoginRepository(
        @ApplicationContext context: Context
    ): LoginRepository {
        return LoginRepoImpl(context)
    }

}