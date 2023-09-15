package com.sungil.wear.presentation.intro.viewModel

import androidx.concurrent.futures.await
import androidx.health.services.client.HealthServices
import androidx.health.services.client.data.DataType
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sungil.wear.presentation.MainApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor() : ViewModel() {

    private var _liveData = MutableLiveData<ViewStatus>()
    val liveData = _liveData

    init {
        checkHealthApi()
    }

    private fun checkHealthApi() {
        _liveData.postValue(ViewStatus.ViewLoading)
        val healthClient = HealthServices.getClient(MainApplication.appContext)
        val measureClient = healthClient.measureClient
        viewModelScope.launch {
            val capabilities = measureClient.getCapabilitiesAsync().await()
            val supportRunningApi = DataType.RUNNING_STEPS in capabilities.supportedDataTypesMeasure
            if(!supportRunningApi){
                _liveData.postValue(ViewStatus.Error("Not Support Device"))
                return@launch
            }
            _liveData.postValue(ViewStatus.HealthLiveData("Support Device"))
        }
    }


    sealed class ViewStatus {
        object ViewLoading : ViewStatus()
        data class HealthLiveData(val data: String) : ViewStatus()
        data class Error(val message: String) : ViewStatus()
    }

}