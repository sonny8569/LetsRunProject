package com.sungil.runningproejct_mvvm.activityRate.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sungil.runningproejct_mvvm.activityRate.useCase.GetRunningRateUseCase
import com.sungil.runningproejct_mvvm.activityRate.useCase.StartRunningRateUseCase
import com.sungil.runningproejct_mvvm.activityRate.useCase.StopRunningRateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RateViewModel @Inject constructor(
    private val getRunningRateUseCase: GetRunningRateUseCase,
    private val stopRunningRateUseCase: StopRunningRateUseCase,
    private val startRunningRateUseCase: StartRunningRateUseCase,
) :
    ViewModel() {

    private var _liveData = MutableLiveData<ViewStatus>()
    val liveData = _liveData


    private fun getKmRate() {
        val data = getRunningRateUseCase.getRunningRate()
        viewModelScope.launch {
            data.collect{
                _liveData.value = ViewStatus.KmRate(it.toString())
            }
        }
    }

    fun startRunningRate() {
        _liveData.postValue(ViewStatus.Loading)
        viewModelScope.launch {
            startRunningRateUseCase.startRunningRate()
        }
        getKmRate()
    }

    fun stopRunningRate(distance: String) {
        viewModelScope.launch {
            stopRunningRateUseCase.stopRunningRate()
        }
        stopRunningRateUseCase.saveRunningRate(distance)
    }

    sealed class ViewStatus {
        object Loading : ViewStatus()
        data class KmRate(val data: String) : ViewStatus()
        data class ViewError(val message: String) : ViewStatus()

    }
}