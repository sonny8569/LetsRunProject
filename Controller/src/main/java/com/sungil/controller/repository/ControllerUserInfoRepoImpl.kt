package com.sungil.controller.repository

import com.sungil.controller.interactor.UserInfoDBDataSource
import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData
import com.sungil.runningproejct_mvvm.domain.interactor.UserInfoRepository
import javax.inject.Inject

class ControllerUserInfoRepoImpl @Inject constructor(private val userInfoDBDataSource: UserInfoDBDataSource) :
    UserInfoRepository {

    override fun saveUserInfo(data: UserInfoData) {
        userInfoDBDataSource.insertUserInfo(data)
    }

    override fun updateUserInfo(data: UserInfoData) {
        userInfoDBDataSource.updateUserInfo(data)
    }

    override fun deleteUserInfo(data: UserInfoData) {
        userInfoDBDataSource.deleteUserInfo(data)
    }

    override fun getUserInfo(): UserInfoData? = userInfoDBDataSource.getUserInfo()
}