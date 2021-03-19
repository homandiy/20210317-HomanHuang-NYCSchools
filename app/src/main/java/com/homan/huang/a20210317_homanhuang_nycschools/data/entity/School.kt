package com.homan.huang.a20210317_homanhuang_nycschools.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
	tableName = "score"
)
data class School(
	@SerializedName("dbn")
	@PrimaryKey(autoGenerate = false)
	val dbn: String? = null,

	@SerializedName("school_name")
	val schoolName: String? = null,

	@SerializedName("overview_paragraph")
	val overviewParagraph: String? = null,
)
