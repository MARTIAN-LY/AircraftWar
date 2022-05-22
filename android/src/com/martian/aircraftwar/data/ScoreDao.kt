package com.martian.aircraftwar.data

import androidx.room.*

@Dao
interface ScoreDao {

//    @Query("SELECT * FROM user")
//    fun getAll(): List<Score>
//
//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<Score>
//
//    @Query(
//        "SELECT * FROM user WHERE first_name LIKE :first AND " +
//                "last_name LIKE :last LIMIT 1"
//    )
//    fun findByName(first: String, last: String): Score
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(score: Score)
//
//    @Insert
//    suspend fun insertAll(vararg scores: Score)
//
//    @Delete
//    fun delete(user: Score)
}