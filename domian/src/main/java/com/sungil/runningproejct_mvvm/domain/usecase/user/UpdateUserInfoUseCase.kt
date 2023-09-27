package com.sungil.runningproejct_mvvm.domain.usecase.user

import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData
import com.sungil.runningproejct_mvvm.domain.interactor.UserInfoRepository
import javax.inject.Inject

class UpdateUserInfoUseCase @Inject constructor(private val userInfoRepository: UserInfoRepository) {

    fun updateUserInfo(data: UserInfoData) {
        userInfoRepository.updateUserInfo(data)
    }
}