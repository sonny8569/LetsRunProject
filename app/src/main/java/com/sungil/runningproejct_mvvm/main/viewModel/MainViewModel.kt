package com.sungil.runningproejct_mvvm.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sungil.runningproejct_mvvm.dataObject.FirebasePostData
import com.sungil.runningproejct_mvvm.dataObject.PostSnsBottomSheet
import com.sungil.runningproejct_mvvm.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
//MainViewModel inject
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    var postData = MutableLiveData<ViewStatus>()

    var setFollowerLiveData = MutableLiveData<ViewStatus>()

    private var follower = ArrayList<String>()

    val postLiveData = MutableLiveData<ViewStatus>()

    var followerLiveData :MutableLiveData<ViewStatus> = MutableLiveData()

    init {
        getFollower()
    }

    private fun getFollower() {
        val data = repository.getUserInfo()
        if (data == null) {
            followerLiveData.postValue(ViewStatus.ViewError("ERROR The User Data is Null"))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val followers = repository.getFollower(data.id)
            followers.collect {
                if (it.isEmpty()) {
                    followerLiveData.postValue(ViewStatus.ViewError("The Follower is Empty"))
                    return@collect
                }
                followerLiveData.postValue(ViewStatus.ViewSuccess("Success to get Follower Please Wait"))
                follower.addAll(it)
                follower.add(data.id)
                clickFollower()
            }
        }
    }

    fun clickFollower() {
        postData.postValue(ViewStatus.ViewLoading)
        viewModelScope.launch(Dispatchers.IO) {
            val followerPost = repository.getFollowerPost(follower)
            followerPost.collect {
                if (it.isEmpty()) {
                    postData.postValue(ViewStatus.ViewError("The Follower Post is Null"))
                    return@collect
                }
                postData.postValue(ViewStatus.ViewSuccess(it))
            }
        }
    }

    fun clickUnFollower() {
        postData.postValue(ViewStatus.ViewLoading)
        viewModelScope.launch(Dispatchers.IO) {
            val unFollowerPostData = repository.getUnFollowerPost(follower)
            unFollowerPostData.collect {
                if (it.size == 0) {
                    postData.postValue(ViewStatus.ViewError("The Data is Null"))
                }
                postData.postValue(ViewStatus.ViewSuccess(it))
            }
        }
    }

    fun writeNewFollower(userId: String) {
        val data = repository.getUserInfo()
        if (data == null) {
            setFollowerLiveData.postValue(ViewStatus.ViewError("ERROR The User Data is null"))
            return
        }
        if (follower.contains(userId)) {
            Timber.e("Already Follower User")
            setFollowerLiveData.postValue(ViewStatus.ViewError("Already Follower user"))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.setNewFollower(userId, data.id)
            result.collect {
                if (it == "Success") {
                    follower.add(userId)
                }
                setFollowerLiveData.postValue(ViewStatus.ViewSuccess(it))
            }
        }
    }


    fun postSns(data: PostSnsBottomSheet, timeStamp: Long) {
        postData.value = ViewStatus.ViewLoading
        val userInfo = repository.getUserInfo()
        val postData = FirebasePostData(
            userInfo!!.id, data.title!!, data.content!!, timeStamp, data.km!!, userInfo.nickName
        )
        viewModelScope.launch {
            val result = repository.writePost(postData)
            postLiveData.postValue(ViewStatus.ViewSuccess(result))
        }

    }

    sealed class ViewStatus {
        object ViewLoading : ViewStatus()
        data class ViewSuccess(val data: Any) : ViewStatus()
        data class ViewError(val message: String) : ViewStatus()
    }
}