package com.homan.huang.a20210317_homanhuang_nycschools.data.room

import com.example.background.helper.lgi
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.network.NycHsApiHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val apiHelper: NycHsApiHelper,
//    schoolDb: SchoolDatabase
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

}