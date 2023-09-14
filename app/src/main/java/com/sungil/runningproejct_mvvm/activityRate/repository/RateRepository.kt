package com.sungil.runningproejct_mvvm.activityRate.repository

import androidx.lifecycle.LiveData


interface RateRepository {
    fun getLocationData()

    fun stopLocationRate()

    fun startLocationRate()

}