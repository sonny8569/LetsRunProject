package com.sungil.runningproejct_mvvm

import android.app.Application
import android.content.Context
import com.sungil.controller.ControllerMainApplication
import com.sungil.runningproejct_mvvm.utill.TimberDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * MainApplication
 */
@HiltAndroidApp
class MainApplication : Application() {
    companion object {
        lateinit var appContext: Context
            private set
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        init()
    }

    private fun init(){
        Timber.plant(TimberDebugTree())
        ControllerMainApplication.appContext = appContext
        ControllerMainApplication().init()
    }

}