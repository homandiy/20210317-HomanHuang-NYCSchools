package com.homan.huang.a20210317_homanhuang_nycschools.data.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.Score

interface ScoreDao {
    @Query("SELECT * FROM score WHERE dbn = :mDbn")
    fun getScores(mDbn: String): List<Score>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(scoreList: List<Score>)

    @Delete
    fun delete(score: Score)

    @Delete
    fun deleteAll(socreList: List<Score>)
}