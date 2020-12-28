package com.itpm_gk.android_it_terminology_search_app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itpm_gk.android_it_terminology_search_app.data.database.dao.ITTerminologyDao
import com.itpm_gk.android_it_terminology_search_app.data.database.entity.ITTerminology

@Database(entities = [ITTerminology::class], version = 1, exportSchema = false)
abstract class ITTerminologyRoomDatabase: RoomDatabase() {

    abstract fun itTerminologyDao(): ITTerminologyDao

    companion object {

        @Volatile
        private var INSTANCE: ITTerminologyRoomDatabase? = null

        fun getDatabase(context: Context): ITTerminologyRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ITTerminologyRoomDatabase::class.java,
                    "it_terminology_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}