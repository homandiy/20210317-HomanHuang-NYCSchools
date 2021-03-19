package com.homan.huang.a20210317_homanhuang_nycschools.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.Score
import com.homan.huang.a20210317_homanhuang_nycschools.data.room.dao.SchoolDao
import com.homan.huang.a20210317_homanhuang_nycschools.data.room.dao.ScoreDao
import com.homan.huang.a20210317_homanhuang_nycschools.helper.DB_NAME


/* School Database has two tables:
    table--school: Holds basic information
    table--score: Holds average scores about the school
*/
@Database(
    entities = [School::class, Score::class],
    version = 1
)
abstract class SchoolDatabase : RoomDatabase() {
    abstract fun schoolDao(): SchoolDao
    abstract fun scoreDao(): ScoreDao

    companion object {
        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        fun getDatabase(context: Context): SchoolDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}