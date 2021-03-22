package com.homan.huang.a20210317_homanhuang_nycschools.data.room.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.background.helper.lgd
import com.google.common.truth.Truth.assertThat
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.data.room.SchoolDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class SchoolDaoTest {

    private lateinit var testDb: SchoolDatabase
    private lateinit var schoolDao: SchoolDao

    @Before
    fun setup() {
        testDb = Room.inMemoryDatabaseBuilder(
                    ApplicationProvider.getApplicationContext(),
                    SchoolDatabase::class.java,
                ).allowMainThreadQueries().build()

        schoolDao = testDb.schoolDao()

    }

    @After
    fun teardown() {
        testDb.close()
    }

    fun getSample1(): School {
        return School("88J888",
            "Test High School",
            "Overview Test High School.",
            "888 street st.",
            "Your",
            "NY",
            "88188",
            "888-444-9999",
            "090-787-2342",
            "uu@yourschool.org",
            "YourSchool.org"
        )
    }

    // sample data 2
    fun getSample2(): School {
        return School("77J888",
            "Test2 High School",
            "Overview2 Test High School.",
            "2338 street bl.",
            "New",
            "NY",
            "33188",
            "333-444-8899",
            "343-787-8842",
            "vv@hisschool.org",
            "HisSchool.org"
        )
    }

    @Test
    fun insertTest() = runBlocking {
        val sample = getSample1()
        schoolDao.insert(sample)
        val result = schoolDao.getSchool(sample.dbn)
        // verify
        assertEquals(result?.schoolName, sample.schoolName)

    }

    @Test
    fun insertAllTest() = runBlocking {
        val schools = listOf<School>(getSample1(), getSample2())
        schoolDao.insertAll(schools)
        val count = schoolDao.getCount()
        // verify
        assertEquals(count, 2)
    }

    @Test
    fun deleteTest() = runBlocking {
        val sample = getSample1()

        schoolDao.insert(sample)
        schoolDao.delete(sample)

        val result = schoolDao.getSchool(sample.dbn)
        // verify
        assertThat(result).isNull()
    }

    @Test
    fun deleteAllTest() = runBlocking {
        val schools = listOf<School>(getSample1(), getSample2())
        schoolDao.insertAll(schools)
        schoolDao.deleteAll()
        val count = schoolDao.getCount()
        // verify
        assertEquals(count, 0)
    }
}