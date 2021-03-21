package com.homan.huang.a20210317_homanhuang_nycschools.data.room.dao

import androidx.room.*
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.Score

@Dao
interface ScoreDao {
    @Query("SELECT * FROM score WHERE dbn = :mDbn")
    suspend fun getScores(mDbn: String): List<Score>?

    @Query("SELECT * FROM score WHERE dbn = :mDbn")
    suspend fun getOne(mDbn: String): Score?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(scoreList: List<Score>)

    @Delete
    suspend fun delete(score: Score)

    @Delete
    suspend fun deleteAll(socreList: List<Score>)

    @Query("SELECT * FROM score")
    suspend fun getAllScores(): List<Score>

    // count all rows
    @Query("SELECT COUNT(dbn) FROM score")
    suspend fun getCount(): Int
}