package com.sungil.device.entity

data class FirebasePostData (
    val writer : String = "",
    val title : String = "",
    val content : String = "",
    val time : Long = 0L,
    val running : String = "",
    val nickName : String = ""
)
