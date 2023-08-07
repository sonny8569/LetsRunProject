package com.sungil.runningproejct_mvvm.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sungil.runningproejct_mvvm.`object`.LoginData
import com.sungil.runningproejct_mvvm.`object`.UserInfo
import com.sungil.runningproejct_mvvm.repository.LoginRepository
import com.sungil.runningproejct_mvvm.utill.Define
import com.sungil.runningproejct_mvvm.utill.ListenerMessage
import com.sungil.runningproejct_mvvm.utill.RepositoryListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Login viewModel
 */
class LoginViewModel(private val repository: LoginRepository) : ViewModel(), RepositoryListener {
    //LiveData 여러개 쓰던가
    //LiveData 하나만?->
    private var _loginLiveData = MutableLiveData<LoginStatus>()
    val loginLiveData get() = _loginLiveData

    //로그인 요청
    fun requestLogin(data: LoginData){
        _loginLiveData.value = LoginStatus.LoginLoading
        viewModelScope.launch (Dispatchers.IO){
            repository.requestLogin(data, this@LoginViewModel)
        }
    }

    //Firebase 에 대한 응답 Listener
    override fun onMessageSuccess(data: ListenerMessage) {
        when(data.message){
            Define.PROP_SAVE_USERINFO ->{
                val userInfo = data.data as UserInfo
                repository.saveUserInfo(userInfo , this )
            }
            else -> _loginLiveData.setValue(LoginStatus.LoginSuccess(data.message))
        }
    }

    override fun onMessageFail(data: ListenerMessage) {
        _loginLiveData.value = LoginStatus.LoginError(data.message)
    }

    sealed class LoginStatus {
        data class LoginSuccess(val data : String) : LoginStatus()
        data class LoginError(val message : String) : LoginStatus()

        object LoginLoading : LoginStatus()

    }
}