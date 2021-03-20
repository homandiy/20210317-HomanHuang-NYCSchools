package com.homan.huang.a20210317_homanhuang_nycschools.ui.main

import android.graphics.ColorSpace.Model


//class SchoolClickListener(val clickListener: (dbn: String) -> Unit) {
//    fun onClick(dbn: String) = clickListener(dbn)
//}

interface SchoolClickListener {
    fun onSchool_item_click(key: String)
}