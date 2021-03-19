package com.homan.huang.a20210317_homanhuang_nycschools.network

import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.Score
import javax.inject.Inject

class NycHsApiHelperImpl @Inject constructor(
    private val apiService: NycHsApiService
): NycHsApiHelper {

    override
    suspend fun getSchoolInfo(): List<School> =
        apiService.getSchoolInfo()


    override
    suspend fun getSchoolScores(): List<Score> =
        apiService.getSchoolScores()


}