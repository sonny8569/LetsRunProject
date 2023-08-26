package com.sungil.runningproejct_mvvm.repository.mainRepositoryImpl

import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sungil.runningproejct_mvvm.appDatabase.RunningDao
import com.sungil.runningproejct_mvvm.appDatabase.UserInfoDao
import com.sungil.runningproejct_mvvm.dataObject.FirebasePostData
import com.sungil.runningproejct_mvvm.dataObject.UserInfoDBM
import com.sungil.runningproejct_mvvm.dataObject.WearRunDataDBM
import com.sungil.runningproejct_mvvm.repository.MainRepository
import com.sungil.runningproejct_mvvm.utill.Define
import com.sungil.runningproejct_mvvm.useCase.MainUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//MainRepo hilt  적용
class MainRepositoryImpl @Inject constructor(private val wearRoomData: RunningDao , private val userDao : UserInfoDao , private val useCase : MainUseCase) : MainRepository {
    private val database = Firebase.database(Define.FIREBASE_BASE_URL)

    override fun getRunningRoomDB(): LiveData<WearRunDataDBM?> {
        return wearRoomData.getRunningData()
    }

    override fun getFollower(userId : String) : Flow<List<String>> = flow {
        val followerUrl = Define.FIREBASE_FOLLOWER +"/"+userId
        val followList = suspendCoroutine<List<String>> {continuation ->
            database.getReference(followerUrl).addListenerForSingleValueEvent(object  :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value as? HashMap<String, String>
                    val followerList = value?.values?.toList() ?: emptyList()
                    continuation.resume(followerList)
                }
                override fun onCancelled(error: DatabaseError) {
                    Timber.e("ERROR to get Follower $error")
                    val errorList : List<String> = listOf(error.message)
                    continuation.resume(errorList)
                }
            })
        }
        emit(followList)
    }

    override fun getUserInfo(): UserInfoDBM? {
        return userDao.getUserInfo() ?: return null
    }

    override fun getFollowerPost(follower: ArrayList<String>) : Flow<ArrayList<FirebasePostData>> = flow {
        val followerUrl = Define.FIREBASE_SNS
        val followerPostData = suspendCoroutine<ArrayList<FirebasePostData>>{
            database.getReference(followerUrl).addValueEventListener(object  :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val followerPost = useCase.getFollowerData(snapshot , follower)
                     it.resume(followerPost)
                }
                override fun onCancelled(error: DatabaseError) {
                    Timber.e("Error to get FollowerPost :$error")
                    it.resumeWithException(error.toException())
                }
            })
        }
        emit(followerPostData)
    }

    override fun getUnFollowerPost(follower: ArrayList<String>): Flow<ArrayList<FirebasePostData>> = flow {
        val followerUrl = Define.FIREBASE_SNS
        val unFollowerPostData = suspendCoroutine<ArrayList<FirebasePostData>> {
            database.getReference(followerUrl).addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val unFollowerPost = useCase.getUnFollowerData(snapshot, follower)
                    it.resume(unFollowerPost)
                }

                override fun onCancelled(error: DatabaseError) {
                    Timber.e("Error to get FollowerPost :$error")
                    it.resumeWithException(error.toException())
                }
            })
        }
        emit(unFollowerPostData)
    }

    override fun setNewFollower(userId: String, myId: String): Flow<String> = flow {
        val followerUrl = Define.FIREBASE_FOLLOWER + "/" + myId + "/" + userId
        val followerRef = database.getReference(followerUrl)
        val setFollowerResult = suspendCoroutine<String> {
            followerRef.setValue(userId) { databaseError, databaseReference ->
                if (databaseError == null) {
                    Timber.d("Success to set Follower")
                    it.resume("Success")
                    return@setValue
                }
                Timber.e("Error to set Follower")
                it.resume("error")
            }
        }
        emit(setFollowerResult)
    }

    override  fun writePost(data: FirebasePostData): Flow<String> = flow  {
        val snsPostUrl = Define.FIREBASE_SNS_test
        val snsPostRef = database.getReference(snsPostUrl)

        val result = suspendCoroutine<String> { continuation ->
            snsPostRef.setValue(data).addOnSuccessListener {
                Timber.d("Success to Post Data")
                continuation.resume("Success")
            }.addOnFailureListener {
                Timber.e("ERROR to Post Data")
                continuation.resume("error")
            }
        }

        emit(result)
    }.flowOn(Dispatchers.IO)

}