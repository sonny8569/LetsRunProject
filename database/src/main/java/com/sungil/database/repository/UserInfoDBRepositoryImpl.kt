package com.sungil.database.repository

import com.sungil.controller.interactor.UserInfoDBDataSource
import com.sungil.database.entity.toDBM
import com.sungil.database.entity.toDomainModel
import com.sungil.device.room.UserInfoDao
import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData
import javax.inject.Inject

class UserInfoDBRepositoryImpl @Inject constructor(
    private val userDao: UserInfoDao,
) : UserInfoDBDataSource {
    override fun updateUserInfo(data: UserInfoData) {
        userDao.update(data.toDBM())
    }

    override fun insertUserInfo(data: UserInfoData) {
        userDao.insert(data.toDBM())
    }

    override fun getUserInfo(): UserInfoData? {
        return userDao.getUserInfo()?.toDomainModel()
    }

    override fun deleteUserInfo(data: UserInfoData) {
        userDao.delete(data.toDBM())
    }

}