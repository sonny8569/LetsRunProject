package com.sungil.device.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sungil.device.USERINFO_TABLE

//기본 init 변수 추가

@Entity(
    tableName = USERINFO_TABLE
)
class UserInfoDBM(
    @PrimaryKey val id : String = "" ,
    @ColumnInfo val password : String = "",
    @ColumnInfo val phoneNumber : String = "",
    @ColumnInfo val nickName : String = ""
)