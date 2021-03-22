package com.homan.huang.a20210317_homanhuang_nycschools.data.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.homan.huang.a20210317_homanhuang_nycschools.data.room.dao.ScoreDao
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class SchoolDatabaseTest {

    private lateinit var testDb: SchoolDatabase
    private lateinit var scoreDao: ScoreDao

    @Before
    fun setup() {
        testDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SchoolDatabase::class.java,
        ).allowMainThreadQueries().build()

        scoreDao = testDb.scoreDao()

    }

    @After
    fun teardown() {
        testDb.close()
    }



}