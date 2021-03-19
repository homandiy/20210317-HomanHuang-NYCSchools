package com.homan.huang.a20210317_homanhuang_nycschools.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.Score


/* School Database has two tables:
    table--school: Holds basic information
    table--score: Holds average scores about the school
*/
@Database(
    entities = [School::class],
    version = 1
)
abstract class SchoolDatabase : RoomDatabase() {
//    abstract fun schoolDao(): SchoolDao
//    abstract fun scoreDao(): ScoreDao
}