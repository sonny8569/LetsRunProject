package com.sungil.runningproejct_mvvm.activityRate.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sungil.runningproejct_mvvm.activityRate.useCase.GetKmRateUseCase
import com.sungil.runningproejct_mvvm.appDatabase.RunningDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RateViewModel @Inject constructor(private val getKmRateUseCase: GetKmRateUseCase , private val runningDao: RunningDao) :
    ViewModel() {

    private var _liveData = runningDao.getRunningDataLiveData()
    val liveData = _liveData


    private fun getKmRate() {
        getKmRateUseCase.getRunningRate()
    }

    fun startRunningRate() {
        getKmRateUseCase.startRunningRate()
        getKmRate()
    }

    fun stopRunningRate() {
        getKmRateUseCase.stopRunningRate()
    }

    sealed class ViewStatus {
        object ViewLoading : ViewStatus()
        data class KmRate(val data: String) : ViewStatus()
        data class ViewError(val message: String) : ViewStatus()

    }
}