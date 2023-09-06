package com.sungil.runningproejct_mvvm.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sungil.runningproejct_mvvm.dataObject.FirebasePostData
import com.sungil.runningproejct_mvvm.dataObject.PostSnsBottomSheet
import com.sungil.runningproejct_mvvm.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.publishOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
//MainViewModel inject
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    var postData = MutableLiveData<MainStatus>()

    var setFollowerLiveData = MutableLiveData<MainStatus>()

    private var follower = ArrayList<String>()

    val postLiveData = MutableLiveData<MainStatus>()

    var followerLiveData = MutableLiveData<MainStatus>()

    init {
        getFollower()
    }

    private fun getFollower() {
        val data = repository.getUserInfo()
        if (data == null) {
            followerLiveData.postValue(MainStatus.MainError("ERROR The User Data is Null"))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val followers = repository.getFollower(data.id)
            followers.collect {
                if (it.isEmpty()) {
                    followerLiveData.postValue(MainStatus.MainError("The Follower is Empty"))
                    return@collect
                }
                followerLiveData.postValue(MainStatus.MainSuccess("Success to get Follower Please Wait"))
                follower.addAll(it)
                follower.add(data.id)
                requestFollowerPost()
            }
        }
    }

    fun requestFollowerPost() {
        postData.postValue(MainStatus.MainLoading)
        viewModelScope.launch(Dispatchers.IO) {
            val followerPost = repository.getFollowerPost(follower)
            followerPost.collect {
                if (it.isEmpty()) {
                    postData.postValue(MainStatus.MainError("The Follower Post is Null"))
                    return@collect
                }
                postData.postValue(MainStatus.MainSuccess(it))
            }
        }
    }

    fun requestUnFollowerPost() {
        postData.postValue(MainStatus.MainLoading)
        viewModelScope.launch(Dispatchers.IO) {
            val unFollowerPostData = repository.getUnFollowerPost(follower)
            unFollowerPostData.collect {
                if (it.size == 0) {
                    postData.postValue(MainStatus.MainError("The Data is Null"))
                }
                postData.postValue(MainStatus.MainSuccess(it))
            }
        }
    }

    fun writeNewFollower(userId: String) {
        val data = repository.getUserInfo()
        if (data == null) {
            setFollowerLiveData.postValue(MainStatus.MainError("ERROR The User Data is null"))
            return
        }
        if (follower.contains(userId)) {
            Timber.e("Already Follower User")
            setFollowerLiveData.postValue(MainStatus.MainError("Already Follower user"))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.setNewFollower(userId, data.id)
            result.collect {
                if (it == "Success") {
                    follower.add(userId)
                }
                setFollowerLiveData.postValue(MainStatus.MainSuccess(it))
            }
        }
    }


    fun postSns(data: PostSnsBottomSheet, timeStamp: Long) {
        postData.value = MainStatus.MainLoading
        val userInfo = repository.getUserInfo()
        val postData = FirebasePostData(
            userInfo!!.id, data.title!!, data.content!!, timeStamp, data.km!!, userInfo.nickName
        )
        viewModelScope.launch {
            val result = repository.writePost(postData)
            postLiveData.postValue(MainStatus.MainSuccess(result))
        }

    }

    sealed class MainStatus {
        object MainLoading : MainStatus()
        data class MainSuccess(val data: Any) : MainStatus()
        data class MainError(val message: String) : MainStatus()
    }
}