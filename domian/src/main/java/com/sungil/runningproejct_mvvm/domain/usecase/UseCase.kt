package com.sungil.runningproejct_mvvm.domain.usecase

interface UseCase<PARAM : UseCase.Param, RESULT : UseCase.Result> {
    suspend fun invoke(param: PARAM): RESULT


    interface Param
    interface Result
}