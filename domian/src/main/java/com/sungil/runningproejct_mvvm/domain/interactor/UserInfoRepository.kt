package com.sungil.runningproejct_mvvm.domain.interactor

import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData

interface UserInfoRepository {

    fun getUserInfo(): UserInfoData?

    fun saveUserInfo(data: UserInfoData)

    fun updateUserInfo(data: UserInfoData)

    fun deleteUserInfo(data: UserInfoData)

}