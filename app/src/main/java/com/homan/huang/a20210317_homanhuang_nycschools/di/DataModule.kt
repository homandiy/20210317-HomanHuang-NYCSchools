package com.homan.huang.a20210317_homanhuang_nycschools.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

//    @Provides
//    @Singleton
//    fun provideAppDatabase(@ApplicationContext appContext: Context): SchoolDatabase =
//        SchoolDatabase.getDatabase(appContext)

//    @Provides
//    @Singleton
//    fun provideRepository(
//        apiHelper: NycHsApiHelper,
//        schoolDb: SchoolDatabase
//    ): Repository = Repository(apiHelper, schoolDb)
}