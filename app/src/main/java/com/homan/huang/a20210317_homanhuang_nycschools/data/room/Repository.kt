package com.homan.huang.a20210317_homanhuang_nycschools.data.room

import com.example.background.helper.lgi
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.network.NycHsApiHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val apiHelper: NycHsApiHelper,
    private val schoolDb: SchoolDatabase
) {
    private var schools: MutableList<School>? = null

    suspend fun getInfo(): List<School>? {
        val info = apiHelper.getSchoolInfo()

        lgi("info count: ${info.size}")

        if (info != null) {
            for (item in info!!) {
                schools?.add(item!!)
            }
        }
        return schools
    }

    // check roomdb status
    fun checkRoomStatus(): Boolean {
        val schoolCount = schoolDb.schoolDao().getCount()
        val scoreCount = schoolDb.scoreDao().getCount()

        return schoolCount> 10 && scoreCount > 10
    }

    suspend fun saveToRoom() {
        // schools to Room
        val schools = apiHelper.getSchoolInfo()
        lgi("school count: ${schools.size}")
        schoolDb.schoolDao().insertAll(schools)

        // scores to Room
        val scores = apiHelper.getSchoolScores()
        lgi("scores record count: ${scores.size}")
        schoolDb.scoreDao().insertAll(scores)
    }

    fun getAllSchools(): List<School> {
        return schoolDb.schoolDao().getSchools()
    }

}