package com.sungil.controller.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent :: class)
class ControllerRepoModule {

    @Provides
    fun providesControllerRepo() : ControllerRepository{
        return ControllerRepositoryImpl()
    }
}