package com.itpm_gk.android_it_terminology_search_app.ui.top

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope

class ITTerminologyTopViewModelFactory(
    private val coroutineScope: CoroutineScope,
    private val context: Context
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ITTerminologyTopViewModel(
            coroutineScope,
            context.applicationContext as Application
        ) as T
    }
}