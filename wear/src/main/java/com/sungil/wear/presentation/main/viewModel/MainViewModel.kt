package com.sungil.wear.presentation.main.viewModel

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sungil.wear.presentation.MainApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {

    private var _liveData = MutableLiveData<ViewStatus>()
    val liveData = _liveData
    private val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val stepCounterSensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    init {
        getRunningData()
    }

    private fun getRunningData() {
        _liveData.postValue(ViewStatus.ViewLading)

        val stepCounterListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val step = event.values[0].toInt()
                _liveData.postValue(ViewStatus.WearRunningData(step))
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                Timber.d("The Sensor Accuracy is Change")
            }

        }

        sensorManager.registerListener(
            stepCounterListener,
            stepCounterSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    sealed class ViewStatus {
        object ViewLading : ViewStatus()

        data class WearRunningData(val running: Int) : ViewStatus()
        data class Error(val errorMessage: String) : ViewStatus()

    }
}