package com.sungil.runningproejct_mvvm.login.useCase

import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData
import com.sungil.runningproejct_mvvm.domain.usecase.user.UpdateUserInfoUseCase
import javax.inject.Inject

class UpdateUserInfoUseCase @Inject constructor(private val updateUserInfoUseCase: UpdateUserInfoUseCase) {

    fun updateUserInfo(data: UserInfoData) {
        updateUserInfoUseCase.updateUserInfo(data)
    }
}