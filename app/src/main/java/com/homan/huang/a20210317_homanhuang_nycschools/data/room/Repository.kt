package com.homan.huang.a20210317_homanhuang_nycschools.data.room

import com.example.background.helper.lgd
import com.example.background.helper.lge
import com.example.background.helper.lgi
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.Score
import com.homan.huang.a20210317_homanhuang_nycschools.network.NycHsApiHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val apiHelper: NycHsApiHelper,
    private val schoolDb: SchoolDatabase
) {
    private var schools: MutableList<School>? = null

    // check roomdb status
    suspend fun checkRoomStatus(): Boolean {
        val schoolCount = schoolDb.schoolDao().getCount()
        val scoreCount = schoolDb.scoreDao().getCount()

        return schoolCount> 10 && scoreCount > 10
    }

    // save to room from rest api
    suspend fun saveToRoom(): Record {
        // schools to Room
        val schools = apiHelper.getSchoolInfo()
        lgi("school count: ${schools.size}")
        schoolDb.schoolDao().insertAll(schools)
        val schoolCount = schoolDb.schoolDao().getCount()

        // scores to Room
        val scores = apiHelper.getSchoolScores()
        lgi("scores record count: ${scores.size}")
        schoolDb.scoreDao().insertAll(scores)
        val scoreCount = schoolDb.scoreDao().getCount()
        return Record(schoolCount, scoreCount)
    }

    suspend fun getAllSchools(): List<School> {
        return schoolDb.schoolDao().getSchools()
    }

    // find the scores according to dbn
    suspend fun getScores(key: String): Score? {
        val item = schoolDb.scoreDao().getScore(key)

        if (item != null)
            lgd("item: ${item?.schoolName}")
        else
            lge("item is null or not found!")

        return item
    }

    // find the school according to dbn
    suspend fun getSchool(key: String): School? {
        val item = schoolDb.schoolDao().getSchool(key)

        if (item != null)
            lgd("item: ${item?.schoolName}")
        else
            lge("item is null or not found!")

        return item
    }

}

data class Record(val schoolCount: Int, val scoreCount: Int)