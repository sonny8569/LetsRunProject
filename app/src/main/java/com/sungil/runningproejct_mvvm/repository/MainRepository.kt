package com.sungil.runningproejct_mvvm.repository

import androidx.lifecycle.LiveData
import com.sungil.runningproejct_mvvm.dataObject.FirebasePostData
import com.sungil.runningproejct_mvvm.dataObject.UserInfoDBM
import com.sungil.runningproejct_mvvm.dataObject.WearRunDataDBM
import kotlinx.coroutines.flow.Flow


interface MainRepository {
    fun getRunningData(): LiveData<WearRunDataDBM?>

    fun getFollower(userId: String): Flow<List<String>>

    fun getUserInfo(): UserInfoDBM?

    fun getFollowerPost(follower: ArrayList<String>): Flow<ArrayList<FirebasePostData>>
    fun getUnFollowerPost(follower: ArrayList<String>): Flow<ArrayList<FirebasePostData>>
    fun setNewFollower(userId: String, myId: String): Flow<String>
    suspend fun writePost(data: FirebasePostData): String
}