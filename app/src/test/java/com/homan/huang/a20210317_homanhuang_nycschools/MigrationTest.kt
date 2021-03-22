package com.homan.huang.a20210317_homanhuang_nycschools

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.data.room.SchoolDatabase
import com.homan.huang.a20210317_homanhuang_nycschools.data.room.migration.Migrate_1_2
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private val TEST_DB = "migration-test"

    @get:Rule
    val helper = MigrationTestHelper(
            InstrumentationRegistry.getInstrumentation(),
            SchoolDatabase::class.java.canonicalName,
            FrameworkSQLiteOpenHelperFactory()
        )

    // sample data for version 1
    private fun buildSchoolToInsert(): ContentValues {
        val school = ContentValues()
        school.put("dbn", "12G345")
        school.put("schoolName", "123 High School")
        school.put("overviewParagraph", "This is the east side school.")
        return school
    }

    // create a room db
    private fun getMigratedRoomDatabase(): SchoolDatabase {
        return Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            SchoolDatabase::class.java,
            TEST_DB
        ).addMigrations(
            Migrate_1_2
        ).build().apply {
            openHelper.writableDatabase
            close()
        }
    }
    
    @Test
    @Throws(IOException::class)
    @ExperimentalCoroutinesApi
    fun migrateAll() {
        // Create the database in version 2
        helper.createDatabase(TEST_DB, 1).apply {
            insert("school",
                SQLiteDatabase.CONFLICT_REPLACE,
                buildSchoolToInsert())
            close()
        }



        val roomDb = getMigratedRoomDatabase()

        // sample data
        val mdao = roomDb.schoolDao()
        val sampleSchool = School(
            "88K888",
            "YouWon High School",
            "Best of the best",
            "You know street",
            "You Know",
            "NY",
            "88888",
            "777-888-9999",
            "666-777-8888",
            "your@school.com",
            "youwon-high-school.edu"
        )

        mdao.insertReg(sampleSchool)

        val school = mdao.getSchoolReg(sampleSchool.dbn)
        assertEquals("YouWon High School", school?.schoolName)

    }
}