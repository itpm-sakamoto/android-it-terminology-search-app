package com.itpm_gk.android_it_terminology_search_app.ui.word_list

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope

class WordListViewModelFactory(
    private val coroutineScope: CoroutineScope,
    private val context: Context
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WordListViewModel(
            coroutineScope,
            context.applicationContext as Application
        ) as T
    }
}