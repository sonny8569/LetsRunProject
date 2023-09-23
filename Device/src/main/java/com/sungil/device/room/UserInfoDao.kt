package com.sungil.device.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sungil.device.USERINFO_TABLE
import com.sungil.device.entity.UserInfoDBM

@Dao
interface UserInfoDao {

    @Insert
    fun insert(data : UserInfoDBM)

    @Delete
    fun delete(data : UserInfoDBM)

    @Update
    fun update(data : UserInfoDBM)

    @Query("SELECT * FROM ${USERINFO_TABLE}")
    fun getUserInfo() : UserInfoDBM?
}