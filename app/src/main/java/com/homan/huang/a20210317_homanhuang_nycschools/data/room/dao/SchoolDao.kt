package com.homan.huang.a20210317_homanhuang_nycschools.data.room.dao

import androidx.room.*
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School

@Dao
interface SchoolDao {
    @Query("SELECT * FROM school WHERE dbn = :mDbn")
    suspend fun getSchool(mDbn: String): List<School>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(schoolList: List<School>)

    @Delete
    suspend fun delete(school: School)

    @Delete
    suspend fun deleteAll(schoolList: List<School>)

    @Query("SELECT * FROM school")
    suspend fun getSchools(): List<School>

    // count all rows
    @Query("SELECT COUNT(dbn) FROM school")
    suspend fun getCount(): Int
}