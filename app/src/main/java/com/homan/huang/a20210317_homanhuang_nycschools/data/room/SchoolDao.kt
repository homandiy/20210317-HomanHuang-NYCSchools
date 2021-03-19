package com.homan.huang.a20210317_homanhuang_nycschools.data.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School

interface SchoolDao {
    @Query("SELECT * FROM school WHERE dbn = :mDbn")
    fun getScores(mDbn: String): List<School>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(schoolList: List<School>)

    @Delete
    fun delete(school: School)

    @Delete
    fun deleteAll(schoolList: List<School>)
}