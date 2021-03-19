package com.homan.huang.a20210317_homanhuang_nycschools.ui.main

import androidx.lifecycle.*
import com.example.background.helper.lgd
import com.example.background.helper.lgi
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.data.room.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOError
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PageViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val schoollist = MutableLiveData<List<School>>()

    init {
        updateStatus()
    }

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it."
    }

    fun setIndex(index: Int) {
        _index.value = index
    }

    @Throws(IOException::class)
    fun updateStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            // if schoolcount < 10 then download
            if (!repository.checkRoomStatus())
                repository.saveToRoom()
            else
                lgi("No need to update from web.")

            lgi("school list size: ${schoollist.value?.size}")

            if (schoollist.value?.size == null) {
                val list = repository.getAllSchools()
                list.sortedBy { it.schoolName }
                lgi("list: ${list.first().schoolName} and ${list.last().schoolName}")

                schoollist.postValue(list)
//                lgd("schoollist added: ${schoollist.value!!.size} from list(${list.size})")
            } else {
                lgi("school list is not empty.")
            }

        }
    }

}