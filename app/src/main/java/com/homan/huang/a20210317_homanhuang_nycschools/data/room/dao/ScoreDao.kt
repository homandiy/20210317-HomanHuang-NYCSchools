package com.homan.huang.a20210317_homanhuang_nycschools.data.room.dao

import androidx.room.*
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.Score

@Dao
interface ScoreDao {
    @Query("SELECT * FROM score WHERE dbn = :mDbn")
    fun getScores(mDbn: String): List<Score>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(scoreList: List<Score>)

    @Delete
    fun delete(score: Score)

    @Delete
    fun deleteAll(socreList: List<Score>)

    // count all rows
    @Query("SELECT COUNT(dbn) FROM score")
    fun getCount(): Int
}