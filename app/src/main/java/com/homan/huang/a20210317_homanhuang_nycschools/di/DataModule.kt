package com.homan.huang.a20210317_homanhuang_nycschools.di

import android.content.Context
import com.homan.huang.a20210317_homanhuang_nycschools.data.room.Repository
import com.homan.huang.a20210317_homanhuang_nycschools.data.room.SchoolDatabase
import com.homan.huang.a20210317_homanhuang_nycschools.network.NycHsApiHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): SchoolDatabase =
        SchoolDatabase.getDatabase(appContext)

    @Provides
    @Singleton
    fun provideRepository(
        apiHelper: NycHsApiHelper,
        schoolDb: SchoolDatabase
    ): Repository = Repository(apiHelper, schoolDb)
}