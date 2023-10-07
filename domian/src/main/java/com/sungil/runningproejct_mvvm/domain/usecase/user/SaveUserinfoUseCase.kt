package com.sungil.runningproejct_mvvm.domain.usecase.user

import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData
import com.sungil.runningproejct_mvvm.domain.interactor.ControllerRepository
import com.sungil.runningproejct_mvvm.domain.interactor.UserInfoRepository
import javax.inject.Inject

class SaveUserinfoUseCase @Inject constructor(private val userInfoRepository: UserInfoRepository) {

    fun saveUserInfo(data: UserInfoData) {
        userInfoRepository.saveUserInfo(data)
    }
}