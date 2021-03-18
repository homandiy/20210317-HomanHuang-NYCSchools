package com.homan.huang.a20210317_homanhuang_nycschools.network

import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.NyHighSchool
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.Scores
import retrofit2.http.GET

interface NyHsApiService {

    @GET("/resource/s3k6-pzi2.json")
    suspend fun getSchoolInfo(): List<NyHighSchool>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSchoolScores(): List<Scores>

}