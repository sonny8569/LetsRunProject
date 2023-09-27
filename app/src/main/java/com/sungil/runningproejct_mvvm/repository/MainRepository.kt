package com.sungil.runningproejct_mvvm.repository

import com.sungil.device.entity.FirebasePostData
import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData
import com.sungil.runningproejct_mvvm.domain.entity.WearRunData


interface MainRepository {

    suspend fun getFollower(userId: String): List<String>

    fun getUserInfo(): UserInfoData?

    suspend fun getFollowerPost(follower: ArrayList<String>): ArrayList<FirebasePostData>
    suspend fun getUnFollowerPost(follower: ArrayList<String>): ArrayList<FirebasePostData>
    suspend fun setNewFollower(userId: String, myId: String): String
    suspend fun writePost(data: FirebasePostData): String

    fun getRunningData() : WearRunData?
}