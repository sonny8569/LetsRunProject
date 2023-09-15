package com.sungil.runningproejct_mvvm.main.viewModel

import androidx.lifecycle.LiveData
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

    private var _viewStateLiveData = MutableLiveData<ViewStatus>()
    val viewStateLiveData: LiveData<ViewStatus> get() = _viewStateLiveData

    private var runningData: String = "0km"

    init {
        getFollower()
    }

    private fun getFollower() {
        _viewStateLiveData.value = ViewStatus.ViewLoading
        val data = repository.getUserInfo()
        if (data == null) {
            _viewStateLiveData.postValue(ViewStatus.ViewError("ERROR The User Data is Null"))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val followers = repository.getFollower(data.id)
            if (followers.isEmpty()) {
                _viewStateLiveData.postValue(ViewStatus.ViewError("The Follower is Empty"))
                return@launch
            }
            _viewStateLiveData.postValue(ViewStatus.Follower("Success to get Follower Please Wait"))
            follower.addAll(followers)
            follower.add(data.id)
            clickFollower()
        }
    }

    fun clickFollower() {
        _viewStateLiveData.postValue(ViewStatus.ViewLoading)
        viewModelScope.launch(Dispatchers.IO) {
            val followerPost = repository.getFollowerPost(follower)
            _viewStateLiveData.postValue(ViewStatus.PostData(followerPost))
        }
    }

    fun clickUnFollower() {
        _viewStateLiveData.postValue(ViewStatus.ViewLoading)
        viewModelScope.launch(Dispatchers.IO) {
            val unFollowerPostData = repository.getUnFollowerPost(follower)
            _viewStateLiveData.postValue(ViewStatus.PostData(unFollowerPostData))
        }
    }

    fun writeNewFollower(userId: String) {
        _viewStateLiveData.postValue(ViewStatus.ViewLoading)
        val data = repository.getUserInfo()
        if (data == null) {
            _viewStateLiveData.postValue(ViewStatus.ViewError("ERROR The User Data is null"))
            return
        }
        if (follower.contains(userId)) {
            Timber.e("Already Follower User")
            _viewStateLiveData.postValue(ViewStatus.ViewError("Already Follower user"))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.setNewFollower(userId, data.id)
            _viewStateLiveData.postValue(ViewStatus.SetNewFollower(result))
        }
    }


    fun postSns(data: PostSnsBottomSheet, timeStamp: Long) {
        _viewStateLiveData.postValue(ViewStatus.ViewLoading)
        val userInfo = repository.getUserInfo()
        val postData = FirebasePostData(
            userInfo!!.id, data.title!!, data.content!!, timeStamp, data.km!!, userInfo.nickName
        )
        viewModelScope.launch {
            val result = repository.writePost(postData)
            _viewStateLiveData.postValue(ViewStatus.SendPost(result))
        }

    }

    fun checkRunningData() {
        _viewStateLiveData.postValue(ViewStatus.ViewLoading)
        val data = repository.getRunningData()
        if (data != null) {
            runningData = data.runData + "km"
        }
    }

    fun getRunningData(): String {
        return runningData
    }

    sealed class ViewStatus {
        object ViewLoading : ViewStatus()

        data class Follower(val data: String) : ViewStatus()
        data class SetNewFollower(val data: String) : ViewStatus()
        data class PostData(val data: ArrayList<FirebasePostData>) : ViewStatus()
        data class SendPost(val data: String) : ViewStatus()

        data class ViewError(val message: String) : ViewStatus()
    }
}