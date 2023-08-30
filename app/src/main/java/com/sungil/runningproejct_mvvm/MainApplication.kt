package com.sungil.runningproejct_mvvm

import android.app.Application
import android.content.Context
import com.sungil.runningproejct_mvvm.utill.TimberDebugTree
import timber.log.Timber

/**
 * MainApplication
 */
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
    }

}