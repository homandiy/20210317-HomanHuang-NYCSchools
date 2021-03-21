package com.homan.huang.a20210317_homanhuang_nycschools.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.Score
import com.homan.huang.a20210317_homanhuang_nycschools.data.room.Repository
import com.homan.huang.a20210317_homanhuang_nycschools.ui.main.InfoStatus.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    // display picker
    val display = MutableLiveData<InfoStatus>()

    init {
        display.value = SCHOOLS
    }
}

enum class InfoStatus {
    SCHOOLS, SAT_SCORES
}