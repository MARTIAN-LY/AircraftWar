package com.martian.aircraftwar.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/** 表，记录我的得分 */

@Entity(tableName = "my_score")
data class Score(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val num: Int,
    val mode: Int,
    val date: String
)