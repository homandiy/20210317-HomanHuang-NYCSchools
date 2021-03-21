package com.homan.huang.a20210317_homanhuang_nycschools.di

import android.content.Context
import androidx.work.WorkManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.homan.huang.a20210317_homanhuang_nycschools.BuildConfig.APP_TOKEN
import com.homan.huang.a20210317_homanhuang_nycschools.network.NycHsApiHelper
import com.homan.huang.a20210317_homanhuang_nycschools.network.NycHsApiHelperImpl
import com.homan.huang.a20210317_homanhuang_nycschools.network.NycHsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val BaseURL = "https://data.cityofnewyork.us"

    // switch to show http log
    val HTTP_DEBUG = true
    // http timeout in second
    val mTimeout = 100L

    val App_Token_Interceptor = Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        requestBuilder.header("X-App-Token", APP_TOKEN)
        chain.proceed(requestBuilder.build())
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        if (HTTP_DEBUG) {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(logger)
                .readTimeout(mTimeout, TimeUnit.SECONDS)
                .connectTimeout(mTimeout, TimeUnit.SECONDS)
                .build()
        } else // debug OFF
            OkHttpClient.Builder()
                .readTimeout(mTimeout, TimeUnit.SECONDS)
                .connectTimeout(mTimeout, TimeUnit.SECONDS)
                .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BaseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NycHsApiService =
        retrofit.create(NycHsApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: NycHsApiHelperImpl): NycHsApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext appContext: Context): WorkManager =
        WorkManager.getInstance(appContext)

}