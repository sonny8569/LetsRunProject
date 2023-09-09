package com.sungil.runningproejct_mvvm.appDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sungil.runningproejct_mvvm.dataObject.WearRunDataDBM
import com.sungil.runningproejct_mvvm.utill.Define

//running data
@Dao
interface RunningDao {

    @Query("SELECT * FROM ${Define.WEAR_RUNNING_TABLE}")
    fun getRunningData () : LiveData<WearRunDataDBM?>

    @Insert
    fun insert(data : WearRunDataDBM)

    @Update
    fun update(data : WearRunDataDBM)

    @Delete
    fun delete(data:  WearRunDataDBM)


}