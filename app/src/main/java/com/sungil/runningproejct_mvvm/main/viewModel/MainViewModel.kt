package com.sungil.runningproejct_mvvm.main.viewModel

import android.view.View
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

    private var follower = ArrayList<String>()

    var liveData = MutableLiveData<ViewStatus>()

    private var runningData : String = "0km"
    init {
        getFollower()
    }

    private fun getFollower() {
        liveData.value = ViewStatus.ViewLoading
        val data = repository.getUserInfo()
        if (data == null) {
            liveData.postValue(ViewStatus.ViewError("ERROR The User Data is Null"))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val followers = repository.getFollower(data.id)
            if (followers.isEmpty()) {
                liveData.postValue(ViewStatus.ViewError("The Follower is Empty"))
                return@launch
            }
            liveData.postValue(ViewStatus.FollowerLiveData("Success to get Follower Please Wait"))
            follower.addAll(followers)
            follower.add(data.id)
            clickFollower()
        }
    }

    fun clickFollower() {
        liveData.postValue(ViewStatus.ViewLoading)
        viewModelScope.launch(Dispatchers.IO) {
            val followerPost = repository.getFollowerPost(follower)
            liveData.postValue(ViewStatus.PostDataLiveData(followerPost))
        }
    }

    fun clickUnFollower() {
        liveData.postValue(ViewStatus.ViewLoading)
        viewModelScope.launch(Dispatchers.IO) {
            val unFollowerPostData = repository.getUnFollowerPost(follower)
            liveData.postValue(ViewStatus.PostDataLiveData(unFollowerPostData))
        }
    }

    fun writeNewFollower(userId: String) {
        liveData.postValue(ViewStatus.ViewLoading)
        val data = repository.getUserInfo()
        if (data == null) {
            liveData.postValue(ViewStatus.ViewError("ERROR The User Data is null"))
            return
        }
        if (follower.contains(userId)) {
            Timber.e("Already Follower User")
            liveData.postValue(ViewStatus.ViewError("Already Follower user"))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.setNewFollower(userId, data.id)
            liveData.postValue(ViewStatus.SetNewFollowerLiveData(result))
        }
    }


    fun postSns(data: PostSnsBottomSheet, timeStamp: Long) {
        liveData.postValue(ViewStatus.ViewLoading)
        val userInfo = repository.getUserInfo()
        val postData = FirebasePostData(
            userInfo!!.id, data.title!!, data.content!!, timeStamp, data.km!!, userInfo.nickName
        )
        viewModelScope.launch {
            val result = repository.writePost(postData)
            liveData.postValue(ViewStatus.SendPostLiveData(result))
        }

    }

    fun checkRunningData() {
        liveData.postValue(ViewStatus.ViewLoading)
        val data = repository.getRunningData()
        if(data != null) {
            runningData=  data.runData+"km"
        }
    }

    fun getRunningData(): String {
        return runningData
    }
    sealed class ViewStatus {
        object ViewLoading : ViewStatus()

        data class FollowerLiveData(val data: String) : ViewStatus()
        data class SetNewFollowerLiveData(val data: String) : ViewStatus()
        data class PostDataLiveData(val data: ArrayList<FirebasePostData>) : ViewStatus()
        data class SendPostLiveData(val data: String) : ViewStatus()

        data class ViewError(val message: String) : ViewStatus()
    }
}