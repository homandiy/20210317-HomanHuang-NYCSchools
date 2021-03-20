package com.homan.huang.a20210317_homanhuang_nycschools.ui.main

import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School

class SchoolClickListener(val clickListener: (dbn: String) -> Unit) {
    fun onClick(dbn: String) = clickListener(dbn)
}