package com.martian.aircraftwar.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ScoreDao {

    @Query("SELECT * FROM my_score ORDER BY num Desc")
    fun readAllData(): LiveData<List<Score>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addScore(score: Score)

    @Delete
    fun deleteScore(score: Score)
}