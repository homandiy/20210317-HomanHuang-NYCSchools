package com.homan.huang.a20210317_homanhuang_nycschools.network

import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.Score

interface NycHsApiHelper {

    suspend fun getSchoolInfo(): List<School>

    suspend fun getSchoolScores(): List<Score>

}