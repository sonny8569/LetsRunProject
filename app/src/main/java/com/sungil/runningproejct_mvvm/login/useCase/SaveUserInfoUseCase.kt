package com.sungil.runningproejct_mvvm.login.useCase

import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData
import com.sungil.runningproejct_mvvm.domain.usecase.user.SaveUserinfoUseCase
import javax.inject.Inject

class SaveUserInfoUseCase @Inject constructor(private val saveUserInfoUseCase: SaveUserinfoUseCase) {

    fun saveUserInfo(data: UserInfoData) {
        saveUserInfoUseCase.saveUserInfo(data)
    }

}