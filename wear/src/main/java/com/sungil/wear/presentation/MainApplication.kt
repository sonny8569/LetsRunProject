package com.sungil.wear.presentation

import android.app.Application
import android.content.Context
import com.sungil.wear.presentation.utill.TimberDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {
    companion object{
        lateinit var appContext : Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        init()
    }

    private fun init(){
        Timber.plant(TimberDebugTree())
    }

}