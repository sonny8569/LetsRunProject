package com.sungil.device

import android.content.Context
import com.sungil.controller.interactor.GpsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent :: class)
class GpsDataSourceModule {

    @Provides
    fun provideGpsDataSource(@ApplicationContext context: Context): GpsDataSource {
        return GpsTracker(context)
    }
}