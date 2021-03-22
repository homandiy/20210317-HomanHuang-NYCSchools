package com.homan.huang.a20210317_homanhuang_nycschools.data.room.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/*
    Version 1: dbn(school id), school_name, overview_paragraph
	version 2
	        Add new columns:
            location        TEXT
            city            TEXT
            stateCode       TEXT
            zip        		TEXT
            phoneNumber     TEXT
            faxNumber       TEXT
            schoolEmail     TEXT
            website			TEXT

 */
val Migrate_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE school ADD COLUMN location TEXT")
        database.execSQL("ALTER TABLE school ADD COLUMN city TEXT")
        database.execSQL("ALTER TABLE school ADD COLUMN stateCode TEXT")
        database.execSQL("ALTER TABLE school ADD COLUMN zip TEXT")
        database.execSQL("ALTER TABLE school ADD COLUMN phoneNumber TEXT")
        database.execSQL("ALTER TABLE school ADD COLUMN faxNumber TEXT")
        database.execSQL("ALTER TABLE school ADD COLUMN schoolEmail TEXT")
        database.execSQL("ALTER TABLE school ADD COLUMN website TEXT")
    }
}
