package com.sungil.runningproejct_mvvm.login.useCase

import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData
import com.sungil.runningproejct_mvvm.domain.usecase.user.GetUserInfoUseCase
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val getUserInfoUseCase: GetUserInfoUseCase) {

    fun getUserInfo(): UserInfoData? {
        return getUserInfoUseCase.getUserInfo()
    }
}