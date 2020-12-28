package com.itpm_gk.android_it_terminology_search_app.data.repository

import com.itpm_gk.android_it_terminology_search_app.data.database.dao.ITTerminologyDao
import com.itpm_gk.android_it_terminology_search_app.data.database.entity.ITTerminology

/**
 * IT用語データを扱うクラス
 */
class ITTerminologyRepository(private val itTerminologyDao: ITTerminologyDao) {

    // ローカルデータベース系
    fun loadAll() = itTerminologyDao.getAllITTerminologyData()

    suspend fun insert(itTerminology: ITTerminology) {
        itTerminologyDao.insert(itTerminology)
    }
}