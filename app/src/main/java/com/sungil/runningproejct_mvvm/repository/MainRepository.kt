package com.sungil.runningproejct_mvvm.repository

import androidx.lifecycle.LiveData
import com.sungil.runningproejct_mvvm.`object`.WearRunDataDBM

interface MainRepository {
    //repository
    fun getRunningRoomDB () : LiveData<WearRunDataDBM?>
}