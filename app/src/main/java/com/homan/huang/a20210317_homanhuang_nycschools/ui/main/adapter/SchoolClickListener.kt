package com.homan.huang.a20210317_homanhuang_nycschools.ui.main.adapter

import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School

// serve for recyclerview of school info
interface SchoolClickListener {
    fun onSchool_item_click(key: School)
}