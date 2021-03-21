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
    suspend fun checkRoomStatus(): Boolean {
        val schoolCount = schoolDb.schoolDao().getCount()
        val scoreCount = schoolDb.scoreDao().getCount()

        return schoolCount> 10 && scoreCount > 10
    }

    // save to room from rest api
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

    suspend fun getAllSchools(): List<School> {
        return schoolDb.schoolDao().getSchools()
    }

    // find the scores according to dbn
    suspend fun getScores(key: String): Score? {
//        val list = schoolDb.scoreDao().getAllScores()
        val item = schoolDb.scoreDao().getOne(key)

        if (item != null)
            lgd("item: ${item?.schoolName}")
        else
            lge("item is null or not found!")

        return item
    }

}