package com.sungil.runningproejct_mvvm.repository

import androidx.lifecycle.LiveData
import com.sungil.runningproejct_mvvm.dataObject.FirebasePostData
import com.sungil.runningproejct_mvvm.dataObject.UserInfoDBM
import com.sungil.runningproejct_mvvm.dataObject.WearRunDataDBM
import kotlinx.coroutines.flow.Flow


interface MainRepository {

    suspend fun getFollower(userId: String): List<String>

    fun getUserInfo(): UserInfoDBM?

    suspend fun getFollowerPost(follower: ArrayList<String>): ArrayList<FirebasePostData>
    suspend fun getUnFollowerPost(follower: ArrayList<String>): ArrayList<FirebasePostData>
    suspend fun setNewFollower(userId: String, myId: String): String
    suspend fun writePost(data: FirebasePostData): String
}