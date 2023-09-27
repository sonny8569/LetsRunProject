package com.sungil.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sungil.database.USERINFO_TABLE
import com.sungil.runningproejct_mvvm.domain.entity.UserInfoData

//기본 init 변수 추가

@Entity(
    tableName = USERINFO_TABLE
)
class UserInfoDBM(
    @PrimaryKey val id: String = "",
    @ColumnInfo val password: String = "",
    @ColumnInfo val phoneNumber: String = "",
    @ColumnInfo val nickName: String = "",
)

fun UserInfoData.toDBM() = UserInfoDBM(id, password, phoneNumber, nickName)

fun UserInfoDBM.toDomainModel() = UserInfoData(id, password, phoneNumber, nickName)