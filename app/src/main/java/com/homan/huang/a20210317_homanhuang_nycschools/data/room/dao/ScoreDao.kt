package com.homan.huang.a20210317_homanhuang_nycschools.data.room.dao

import androidx.room.*
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.Score

@Dao
interface ScoreDao {
    // Get
    @Query("SELECT * FROM score")
    suspend fun getAllScores(): List<Score>

    @Query("SELECT * FROM score WHERE dbn = :mDbn")
    suspend fun getScore(mDbn: String): Score?

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(scoreList: List<Score>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(score: Score)

    // Delete
    @Delete
    suspend fun delete(score: Score)

    @Query("DELETE FROM score")
    suspend fun deleteAll()

    // count all rows
    @Query("SELECT COUNT(dbn) FROM score")
    suspend fun getCount(): Int
}