package com.sungil.controller.interactor

import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData

interface UserInfoDBDataSource {
    fun updateUserInfo(data : UserInfoData)

    fun insertUserInfo(data : UserInfoData)
    fun getUserInfo() : UserInfoData?

    fun deleteUserInfo(data : UserInfoData)
}