package com.sungil.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sungil.database.WEAR_RUNNING_TABLE
import com.sungil.runningproejct_mvvm.domain.entity.WearRunData

@Entity(
    tableName = WEAR_RUNNING_TABLE
)
//wear 에서 올 데이터
class WearRunDataDBM(
    val runData: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


fun WearRunData.toDBM() = WearRunDataDBM(runData).apply {
    this.id = this@toDBM.id
}

fun WearRunDataDBM.toDomainModel() = WearRunData(id, runData)
