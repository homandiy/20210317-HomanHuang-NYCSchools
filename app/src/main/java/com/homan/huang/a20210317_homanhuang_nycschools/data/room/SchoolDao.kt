package com.homan.huang.a20210317_homanhuang_nycschools.data.room

import androidx.room.*
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School

@Dao
interface SchoolDao {
    @Query("SELECT * FROM school WHERE dbn = :mDbn")
    fun getSchool(mDbn: String): List<School>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(schoolList: List<School>)

    @Delete
    fun delete(school: School)

    @Delete
    fun deleteAll(schoolList: List<School>)

    @Query("SELECT * FROM school")
    fun getSchools(): List<School>
}