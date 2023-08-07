package com.sungil.runningproejct_mvvm.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sungil.runningproejct_mvvm.appDatabase.AppDatabase

//Firebase 에 보내고 받을 회원정보 ( 회원가입시 에만)

@Entity(
    tableName = AppDatabase.USERINFO_TABLE
)
class UserInfo(
    @PrimaryKey val id : String = "" ,
    @ColumnInfo val password : String = "",
    @ColumnInfo val phoneNumber : String = "",
    @ColumnInfo val nickName : String = ""
)