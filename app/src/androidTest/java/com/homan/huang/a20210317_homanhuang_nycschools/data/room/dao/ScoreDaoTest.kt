package com.homan.huang.a20210317_homanhuang_nycschools.data.room.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.Score
import com.homan.huang.a20210317_homanhuang_nycschools.data.room.SchoolDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ScoreDaoTest {

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

    // sample data 1
    fun getSample1(): Score {
        return Score("88J888",
            "300",
            "425",
            "522",
            "Your School 1",
            "344"
        )
    }

    // sample data 2
    fun getSample2(): Score {
        return Score("00J888",
            "400",
            "225",
            "422",
            "Your School 2",
            "644"
        )
    }


    @Test
    fun insertTest() = runBlocking {
        val sample = getSample1()
        scoreDao.insert(sample)
        val result = scoreDao.getScore(sample.dbn)
        // verify
        assertEquals(result?.schoolName, sample.schoolName)
    }

    @Test
    fun insertAllTest() = runBlocking {
        val scores = listOf(getSample1(), getSample2())
        scoreDao.insertAll(scores)
        val count = scoreDao.getCount()
        // verify
        assertEquals(count, 2)
    }

    @Test
    fun deleteTest() = runBlocking {
        val sample = getSample1()

        scoreDao.insert(sample)
        scoreDao.delete(sample)

        val result = scoreDao.getScore(sample.dbn)
        // verify
        assertThat(result).isNull()
    }

    @Test
    fun deleteAllTest() = runBlocking {
        val schools = listOf(getSample1(), getSample2())
        scoreDao.insertAll(schools)
        scoreDao.deleteAll()
        val count = scoreDao.getCount()
        // verify
        assertEquals(count, 0)
    }
}