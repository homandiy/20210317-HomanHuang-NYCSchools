package com.homan.huang.a20210317_homanhuang_nycschools.ui.main

import androidx.lifecycle.*
import com.example.background.helper.lgd
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.Score
import com.homan.huang.a20210317_homanhuang_nycschools.data.room.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolScoresViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    // school scores
    val scores = MutableLiveData<Score?>()
    val error = MutableLiveData<Boolean>()
    var _key = ""

    init {
        scores.value = null
        error.value = false
    }

    // store key for searching
    fun setKey(key: String) {
        _key = key
    }

    // search SAT scores
    fun getScore() {
        if (_key != "") {
            viewModelScope.launch(Dispatchers.IO) {
                val mItem = repository.getScores(_key)
                if (mItem != null)
                    scores.postValue(mItem!!)
                else
                    error.postValue(true)
            }
        }
    }

}