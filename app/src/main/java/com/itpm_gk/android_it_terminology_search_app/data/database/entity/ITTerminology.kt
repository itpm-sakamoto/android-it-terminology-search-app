package com.itpm_gk.android_it_terminology_search_app.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "it_terminology_table")
data class ITTerminology(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "display_name") val display_name: String,
    @ColumnInfo(name = "description") val description: String
)