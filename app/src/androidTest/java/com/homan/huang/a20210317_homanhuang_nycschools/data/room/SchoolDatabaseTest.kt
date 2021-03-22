package com.homan.huang.a20210317_homanhuang_nycschools.data.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.SmallTest
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.data.room.dao.SchoolDao
import com.homan.huang.a20210317_homanhuang_nycschools.data.room.migration.Migrate_1_2
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SchoolDatabaseTest {

    private lateinit var testDb: SchoolDatabase
    private lateinit var schoolDao: SchoolDao

    @Before
    fun setup() {
        testDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SchoolDatabase::class.java,
        ).addMigrations(Migrate_1_2)
            .allowMainThreadQueries().build()

        schoolDao = testDb.schoolDao()

    }

    @After
    fun teardown() {
        testDb.close()
    }

    // sample data 1
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


    // prove migration is working
    @Test
    fun insertTest() = runBlocking {
        val sample = getSample1()
        schoolDao.insert(sample)
        val result = schoolDao.getSchool(sample.dbn)
        // verify new part
        assertEquals(result?.location, sample.location)
        assertEquals(result?.city, sample.city)
        assertEquals(result?.stateCode, sample.stateCode)
        assertEquals(result?.zip, sample.zip)
        assertEquals(result?.phoneNumber, sample.phoneNumber)
        assertEquals(result?.faxNumber, sample.faxNumber)
        assertEquals(result?.schoolEmail, sample.schoolEmail)
        assertEquals(result?.website, sample.website)
    }


}