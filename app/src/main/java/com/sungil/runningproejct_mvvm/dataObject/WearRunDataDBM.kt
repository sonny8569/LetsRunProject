package com.sungil.runningproejct_mvvm.dataObject

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sungil.runningproejct_mvvm.utill.Define

@Entity(
    tableName =  Define.WEAR_RUNNING_TABLE
)
//wear 에서 올 데이터
 class WearRunDataDBM(
    val runData : String
){
     @PrimaryKey(autoGenerate = true)
     var id : Int = 0
}
