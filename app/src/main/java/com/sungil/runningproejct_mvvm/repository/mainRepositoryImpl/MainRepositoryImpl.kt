package com.sungil.runningproejct_mvvm.repository.mainRepositoryImpl

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sungil.device.room.RunningDao
import com.sungil.device.room.UserInfoDao
import com.sungil.device.entity.FirebasePostData
import com.sungil.device.entity.UserInfoDBM
import com.sungil.device.entity.WearRunDataDBM
import com.sungil.runningproejct_mvvm.repository.MainRepository
import com.sungil.runningproejct_mvvm.useCase.GetUnFollowerUseCase
import com.sungil.runningproejct_mvvm.utill.Define
import com.sungil.runningproejct_mvvm.useCase.GetFollowerUseCase
import timber.log.Timber
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

//MainRepo hilt  적용
class MainRepositoryImpl @Inject constructor(
    private val runningDao: RunningDao,
    private val userDao: UserInfoDao,
    private val getFollowerUseCase: GetFollowerUseCase,
    private val unFollowerUseCase: GetUnFollowerUseCase,
) : MainRepository {
    private val database = Firebase.database(Define.FIREBASE_BASE_URL)

    override suspend fun getFollower(userId: String): List<String> {
        val followerUrl = Define.FIREBASE_FOLLOWER + "/" + userId
        val followerRef = database.getReference(followerUrl)
        val dataSnapShot = followerRef.get().await()
        val value = dataSnapShot.value as? HashMap<String, String>
        return value?.values?.toList() ?: emptyList()
    }

    override fun getUserInfo(): UserInfoDBM? {
        return userDao.getUserInfo() ?: return null
    }

    override suspend fun getFollowerPost(follower: ArrayList<String>): ArrayList<FirebasePostData> {
        val followerUrl = Define.FIREBASE_SNS
        val followerPostRef = database.getReference(followerUrl)
        val dataSnapShot = followerPostRef.get().await()
        return getFollowerUseCase.getFollowerData(dataSnapShot, follower)
    }

    override suspend fun getUnFollowerPost(follower: ArrayList<String>): ArrayList<FirebasePostData> {
        val followerUrl = Define.FIREBASE_SNS
        val unFollowerPostRef = database.getReference(followerUrl)
        val dataSnapShot = unFollowerPostRef.get().await()
        return unFollowerUseCase.getUnFollowerData(dataSnapShot, follower)
    }

    override suspend fun setNewFollower(userId: String, myId: String): String {
        val followerUrl = Define.FIREBASE_FOLLOWER + "/" + myId + "/" + userId
        val followerRef = database.getReference(followerUrl)
        var result: String = ""
        followerRef.setValue(userId).addOnSuccessListener {
            Timber.d("Success to Set New Follower")
            result = "success"
        }.addOnFailureListener {
            Timber.e("ERROR to Set New Follower")
            result = "error to Set New Follower"
        }.await()
        return result
    }


    override suspend fun writePost(data: FirebasePostData): String {
        val snsPostUrl = Define.FIREBASE_SNS + "/" + data.writer
        val snsPostRef = database.getReference(snsPostUrl)
        var resultString = ""

        snsPostRef.setValue(data).addOnSuccessListener {
            Timber.d("Success to Post Data")
            resultString = "success"
        }.addOnFailureListener {
            Timber.e("ERROR to Post Data")
            resultString = "error To Write Post"
        }.await()

        return resultString
    }

    override fun getRunningData(): WearRunDataDBM? {
        return runningDao.getRunningData()
    }

}