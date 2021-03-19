package com.homan.huang.a20210317_homanhuang_nycschools.network

import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.Score
import retrofit2.http.GET

interface NycHsApiService {

    @GET("/resource/s3k6-pzi2.json")
    suspend fun getSchoolInfo(): List<School>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSchoolScores(): List<Score>

}