package com.sungil.runningproejct_mvvm.repository

import com.sungil.runningproejct_mvvm.`object`.LoginData
import com.sungil.runningproejct_mvvm.`object`.UserInfoDBM
import com.sungil.runningproejct_mvvm.utill.RepositoryListener
import java.util.concurrent.Flow

/**
 * 로그인 및 회원가입 Repository Interface
 */
interface LoginRepository {

    suspend fun requestCheckUserInfo(data : UserInfoDBM , Listener : RepositoryListener)

    suspend fun requestSignUp(data : UserInfoDBM, Listener : RepositoryListener )

    suspend fun requestLogin(data : LoginData , Listener: RepositoryListener)

    fun saveUserInfo(data : UserInfoDBM , Listener: RepositoryListener)
}