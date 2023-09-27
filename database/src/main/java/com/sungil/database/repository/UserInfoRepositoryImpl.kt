package com.sungil.database.repository

import com.sungil.database.entity.toDBM
import com.sungil.database.entity.toDomainModel
import com.sungil.device.room.UserInfoDao
import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData
import com.sungil.runningproejct_mvvm.domain.interactor.UserInfoRepository
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val userDao: UserInfoDao,
) : UserInfoRepository {
    override fun getUserInfo(): UserInfoData? {
        return userDao.getUserInfo()?.toDomainModel()
    }

    override fun saveUserInfo(data: UserInfoData) {
        userDao.insert(data.toDBM())
    }

    override fun updateUserInfo(data: UserInfoData) {
        userDao.update(data.toDBM())
    }

    override fun deleteUserInfo(data: UserInfoData) {
        userDao.delete(data.toDBM())
    }
}