package com.sungil.controller

import android.app.Application
import android.content.Context
import com.sungil.controller.utill.TimberDebugTree
import com.sungil.device.DeviceMainApplication
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

class ControllerMainApplication : Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        init()
    }

    fun init() {
        Timber.plant(TimberDebugTree())
        DeviceMainApplication.appContext = appContext
    }
}