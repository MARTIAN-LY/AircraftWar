package com.martian.aircraftwar.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/** 对应表 */
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val firstName: String,
    val secondName: String,
    val age: Int
)