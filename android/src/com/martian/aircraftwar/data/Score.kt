package com.martian.aircraftwar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Score(
    @PrimaryKey val rank: Int,
    @ColumnInfo(name = "昵称") val name: String?,
    @ColumnInfo(name = "账号") val id: String?,
    @ColumnInfo(name = "得分") val score: Int,
    @ColumnInfo(name = "时间") val time: Date,
)