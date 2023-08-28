package com.sungil.runningproejct_mvvm.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sungil.runningproejct_mvvm.dataObject.FirebasePostData
import com.sungil.runningproejct_mvvm.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
//MainViewModel inject
@HiltViewModel
class MainViewModel @Inject constructor  (private val repository: MainRepository ): ViewModel()  {
    //repository
    var wearLiveData = repository.getRunningRoomDB()

    var followerPostLiveData = MutableLiveData<ArrayList<FirebasePostData>>()

    var unFollowerPostLiveData = MutableLiveData<ArrayList<FirebasePostData>>()

    var setFollowerLiveData = MutableLiveData<String>()

    var errorLiveData = MutableLiveData<String>()

    private var follower = ArrayList<String>()

    val postLiveData = MutableLiveData<String>()

    fun getFollower() = viewModelScope.launch(Dispatchers.IO) {
        val data = repository.getUserInfo()
        if(data == null){
            errorLiveData.postValue("User Data is Null ERROR")
            return@launch
        }
        try{
            val followers = repository.getFollower(data.id)
            followers.collect {
                follower.addAll(it)
                requestFollowerPost()
            }
        }catch (e : Exception){
            Timber.e("ERROR to get Follower Data")
            errorLiveData.postValue(" ERROR to get Follower Data")
        }
    }

    fun requestFollowerPost() = viewModelScope.launch(Dispatchers.IO) {
        try{
            val followerPost = repository.getFollowerPost(follower)
            followerPost.collect {
                followerPostLiveData.postValue(it)
            }
        }catch (e : Exception){
            Timber.e("Error to get Follower Post")
            errorLiveData.postValue("error to get Follower Post")
        }
    }

    fun requestUnFollowerPost()  = viewModelScope.launch(Dispatchers.IO){
        try{
            val unFollowerPostData = repository.getUnFollowerPost(follower)
            unFollowerPostData.collect{
                unFollowerPostLiveData.postValue(it)
            }
        }catch (e :Exception){
            errorLiveData.postValue( "ERROR to get UnFollower Post Data")
        }
    }

    fun writeNewFollower(userId : String) = viewModelScope.launch(Dispatchers.IO){
        val data = repository.getUserInfo()
        if(data == null){
            errorLiveData.postValue("User Data is Null ERROR ")
            return@launch
        }
        if(follower.contains(userId)){
            Timber.e("Already Follower User")
            errorLiveData.postValue("Already Follower")
            return@launch
        }
        val result = repository.setNewFollower(userId , data.id)
        result.collect{
            if(it == "Success"){
                follower.add(userId)
            }
            setFollowerLiveData.postValue(it)
        }
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