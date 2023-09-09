package com.sungil.runningproejct_mvvm.dataObject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sungil.runningproejct_mvvm.utill.Define

//기본 init 변수 추가

@Entity(
    tableName = Define.USERINFO_TABLE
)
class UserInfoDBM(
    @PrimaryKey val id : String = "" ,
    @ColumnInfo val password : String = "",
    @ColumnInfo val phoneNumber : String = "",
    @ColumnInfo val nickName : String = ""
)