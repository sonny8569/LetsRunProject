package com.sungil.device

import android.app.Application
import android.content.Context
import com.sungil.device.utill.TimberDebugTree
import timber.log.Timber

class DeviceMainApplication : Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        Timber.plant(TimberDebugTree())
    }


}