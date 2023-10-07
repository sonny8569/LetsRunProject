package com.sungil.runningproejct_mvvm.login.module

import android.content.Context
import com.sungil.runningproejct_mvvm.repository.loginImpl.LoginRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingUpLoginImplModule {

    @Provides
    @Singleton
    fun provideSignUpLoginRepository(
        @ApplicationContext context: Context,
    ): LoginRepoImpl {
        return LoginRepoImpl(
            context,
        )
    }
}