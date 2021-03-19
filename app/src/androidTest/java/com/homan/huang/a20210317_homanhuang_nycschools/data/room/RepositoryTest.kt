package com.homan.huang.a20210317_homanhuang_nycschools.data.room

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import assertk.assertThat
import assertk.assertions.isGreaterThan
import assertk.assertions.isNotNull
import com.homan.huang.a20210317_homanhuang_nycschools.network.NycHsApiHelper
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.robolectric.annotation.Config
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
//@RunWith(RobolectricTestRunner::class)
class RepositoryTest {

//    @get:Rule(order = 0)
//    val hiltRule by lazy { HiltAndroidRule(this) }

//    @get:Rule(order = 1) // 2
//    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
//        ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiHelper: NycHsApiHelper

    @Before
    fun setup() {
        hiltRule.inject()

    }


    @Test
    fun testRepository() {
        assertThat(apiHelper).isNotNull()

        val repo = Repository(apiHelper, schoolDb)
        assertThat(repo).isNotNull()

        runBlocking {
            val mList = repo.getInfo()
            assertThat(mList).isNotNull()
            assertThat(mList!!.size).isGreaterThan(1)
            println("\n\nitem counted: ${mList.size}")
        }

    }
}