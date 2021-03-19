package com.homan.huang.a20210317_homanhuang_nycschools.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
	tableName = "school"
)
data class School(
	@SerializedName("dbn")
	@PrimaryKey(autoGenerate = false)
	val dbn: String,

	@SerializedName("school_name")
	@ColumnInfo(name = "school_name")
	val schoolName: String,

	@SerializedName("overview_paragraph")
	@ColumnInfo(name = "overview_paragraph")
	val overviewParagraph: String
)
