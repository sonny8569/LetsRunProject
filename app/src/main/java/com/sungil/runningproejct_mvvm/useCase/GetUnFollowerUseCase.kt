package com.sungil.runningproejct_mvvm.useCase

import com.google.firebase.database.DataSnapshot
import com.sungil.runningproejct_mvvm.dataObject.FirebasePostData
import javax.inject.Inject

class GetUnFollowerUseCase @Inject constructor() {
    fun getUnFollowerData(snapshot : DataSnapshot, follower : ArrayList<String>): ArrayList<FirebasePostData> {
        val unFollowerMap = snapshot.value as? HashMap<String, HashMap<String, Any>>
        val unFollowerPost = ArrayList<FirebasePostData>()
        unFollowerMap?.forEach { (email, innerMap) ->
            if(!follower.contains(email)){
                // innerMap에서 필요한 데이터 추출
                var runningDistance = innerMap["running "]?.toString()?.trim() ?: ""
                if(runningDistance == ""){
                    runningDistance =  innerMap["running"]?.toString()?.trim() ?: ""
                }
                val runningTime = innerMap["time"] as? Long ?: 0L
                val title = innerMap["title"] as? String ?: ""
                val content = innerMap["content"] as? String ?: ""
                val nickname = innerMap["nickName"] as? String ?: ""
                // PostData 객체 생성
                val firebasePostData = FirebasePostData(email ,title, content, runningTime, runningDistance,nickname)

                unFollowerPost.add(firebasePostData)
            }
        }
        return unFollowerPost
    }
}