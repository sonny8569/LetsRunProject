package com.sungil.device.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sungil.database.WEAR_RUNNING_TABLE
import com.sungil.database.entity.WearRunDataDBM

//running data
@Dao
interface RunningDao {

    @Query("SELECT * FROM ${WEAR_RUNNING_TABLE}")
    fun getRunningDataLiveData () : LiveData<WearRunDataDBM?>

    @Insert
    fun insert(data : WearRunDataDBM)

    @Update
    fun update(data : WearRunDataDBM)

    @Delete
    fun delete(data: WearRunDataDBM)

    @Query("SELECT * FROM ${WEAR_RUNNING_TABLE}")
    fun getRunningData () : WearRunDataDBM?


}