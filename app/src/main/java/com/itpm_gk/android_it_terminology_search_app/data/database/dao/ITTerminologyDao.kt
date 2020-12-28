package com.itpm_gk.android_it_terminology_search_app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itpm_gk.android_it_terminology_search_app.data.database.entity.ITTerminology

@Dao
interface ITTerminologyDao {

    @Query("SELECT * FROM it_terminology_table ORDER BY id ASC")
    fun getAllITTerminologyData(): List<ITTerminology>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itTerminology: ITTerminology)
}