package com.sungil.runningproejct_mvvm.repository

import com.sungil.device.entity.FirebasePostData
import com.sungil.device.entity.UserInfoDBM
import com.sungil.device.entity.WearRunDataDBM


interface MainRepository {

    suspend fun getFollower(userId: String): List<String>

    fun getUserInfo(): UserInfoDBM?

    suspend fun getFollowerPost(follower: ArrayList<String>): ArrayList<FirebasePostData>
    suspend fun getUnFollowerPost(follower: ArrayList<String>): ArrayList<FirebasePostData>
    suspend fun setNewFollower(userId: String, myId: String): String
    suspend fun writePost(data: FirebasePostData): String

    fun getRunningData() : WearRunDataDBM?
}