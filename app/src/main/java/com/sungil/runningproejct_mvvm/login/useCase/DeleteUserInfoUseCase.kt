package com.sungil.runningproejct_mvvm.login.useCase

import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData
import com.sungil.runningproejct_mvvm.domain.usecase.user.DeleteUserInfoUseCase
import javax.inject.Inject

class DeleteUserInfoUseCase @Inject constructor(private val deleteUserInfoUseCase: DeleteUserInfoUseCase) {

    fun deleteUserInfo(data: UserInfoData) {
        deleteUserInfoUseCase.deleteUserInfo(data)
    }
}