package com.sungil.runningproejct_mvvm.main.viewModel

import androidx.lifecycle.ViewModel
import com.sungil.runningproejct_mvvm.repository.MainRepository
import com.sungil.runningproejct_mvvm.utill.ListenerMessage
import com.sungil.runningproejct_mvvm.utill.RepositoryListener

class MainViewModel(private val repository: MainRepository) : ViewModel() , RepositoryListener {
<<<<<<< HEAD
<<<<<<< HEAD
//    val wearLiveData get() = repository.getRunningRoomDB()
=======
    val wearLiveData get() = repository.getRunningRoomDB()
>>>>>>> 3a7f4ee (Main쪽 구현)
=======
//    val wearLiveData get() = repository.getRunningRoomDB()
>>>>>>> 905652d (Appdatabase 기존 -> 직접 싱글톤 으로 구현 현제 -> hilt 적용)
    //repository
    override fun onMessageSuccess(data: ListenerMessage) {
        TODO("Not yet implemented")
    }

    override fun onMessageFail(data: ListenerMessage) {
        TODO("Not yet implemented")
    }

}