package com.sungil.runningproejct_mvvm.repository

import com.sungil.device.entity.LoginData
import com.sungil.device.entity.UserInfoDBM
import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData
import com.sungil.runningproejct_mvvm.utill.RepositoryListener

/**
 * 로그인 및 회원가입 Repository Interface
 */
interface LoginRepository {

    suspend fun requestCheckUserInfo(data : UserInfoDBM, Listener : RepositoryListener)

    suspend fun requestSignUp(data : UserInfoDBM, Listener : RepositoryListener )

    suspend fun requestLogin(data : LoginData, Listener: RepositoryListener)

    fun saveUserInfo(data : UserInfoData, Listener: RepositoryListener)
}