package com.sungil.runningproejct_mvvm.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sungil.runningproejct_mvvm.dataObject.FirebasePostData
import com.sungil.runningproejct_mvvm.repository.MainRepository
import com.sungil.runningproejct_mvvm.utill.ListenerMessage
import com.sungil.runningproejct_mvvm.utill.RepositoryListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
//MainViewModel inject
@HiltViewModel
class MainViewModel @Inject constructor  (private val repository: MainRepository ): ViewModel()  {
    //repository
    var wearLiveData = repository.getRunningRoomDB()

    var followerPostLiveData = repository.getFollowerPostLiveData()

    var unFollowerPostLiveData = repository.getUnFollowerPostLiveData()

    var setFollowerLiveData = repository.getSetFollowerLiveData()

    private var follower = ArrayList<String>()

    val postLiveData = MutableLiveData<String>()

    fun getFollower() = viewModelScope.launch(Dispatchers.IO) {
        val data = repository.getUserInfo()
        if (data != null) {
            val followers = repository.getFollower(data.id)
            followers.collect {
                follower.addAll(it)
                getFollowerPost()
            }
        }
    }

    fun getFollowerPost() {
        repository.getFollowerPost(follower)
    }

    fun getUnFollowerPost() {
        repository.getUnFollowerPost(follower)
    }

    fun writeNewFollower(userId : String){
        val data = repository.getUserInfo() ?: return
        if(follower.contains(userId)){
            Timber.e("Already Follower User")
            return
        }
        repository.setNewFollower(userId , data.id)
    }
    fun setFollower(followers : ArrayList<String>){
        this.follower = followers
    }

    fun postSns() = viewModelScope.launch(Dispatchers.IO) {

        val userInfo = repository.getUserInfo()
        var data = FirebasePostData(
            userInfo!!.id, "test2입니다", "test2", 1692804242263, "123km", userInfo.nickName
        )

        val result = repository.writePost(data)
        result.collect {
            postLiveData.postValue(it)
        }
    }


}