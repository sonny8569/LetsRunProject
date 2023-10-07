package com.sungil.controller.interactor

import androidx.lifecycle.LiveData
import com.sungil.runningproejct_mvvm.domain.entity.WearRunData

interface RunningDataSource {
    //TODO 멘토님 해당 LiveData 은 Wear 쪽에서 구현 하고 App 으로 데이터베이스에 전송시 사용하려는건데 Wear 로
    //TODO 구현이 불가능해 지금은 필요 없는 함수 인걸 제가 삭제를 못했습니다.
//    fun getRunningDataLiveData () : LiveData<WearRunData?>

    fun insert(data : WearRunData)

    fun update(data : WearRunData)

    fun delete(data:  WearRunData)

    fun getRunningData () : WearRunData?
}