package com.homan.huang.a20210317_homanhuang_nycschools.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
	tableName = "score"
)
data class Score(

	@SerializedName("dbn")
	@PrimaryKey(autoGenerate = false)
	val dbn: String? = null,

	@SerializedName("sat_writing_avg_score")
	val satWritingAvgScore: String? = null,

	@SerializedName("sat_critical_reading_avg_score")
	val satCriticalReadingAvgScore: String? = null,

	@SerializedName("sat_math_avg_score")
	val satMathAvgScore: String? = null,

	@SerializedName("school_name")
	val schoolName: String? = null,

	@SerializedName("num_of_sat_test_takers")
	val numOfSatTestTakers: String? = null
)
