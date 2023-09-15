package com.sungil.runningproejct_mvvm.activityRate.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sungil.runningproejct_mvvm.activityRate.useCase.GetRunningRateUseCase
import com.sungil.runningproejct_mvvm.activityRate.useCase.StartRunningRateUseCase
import com.sungil.runningproejct_mvvm.activityRate.useCase.StopRunningRateUseCase
import com.sungil.runningproejct_mvvm.appDatabase.RunningDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RateViewModel @Inject constructor(
    private val getRunningRateUseCase: GetRunningRateUseCase,
    private val stopRunningRateUseCase: StopRunningRateUseCase,
    private val startRunningRateUseCase: StartRunningRateUseCase,
    private val runningDao: RunningDao,
) :
    ViewModel() {

    private var _liveData = runningDao.getRunningDataLiveData()
    val liveData = _liveData


    private fun getKmRate() {
        getRunningRateUseCase.getRunningRate()
    }

    fun startRunningRate() {
        viewModelScope.launch {
            startRunningRateUseCase.startRunningRate()
        }
        getKmRate()
    }

    fun stopRunningRate() {
        stopRunningRateUseCase.stopRunningRate()
    }

    sealed class ViewStatus {
        object ViewLoading : ViewStatus()
        data class KmRate(val data: String) : ViewStatus()
        data class ViewError(val message: String) : ViewStatus()

    }
}