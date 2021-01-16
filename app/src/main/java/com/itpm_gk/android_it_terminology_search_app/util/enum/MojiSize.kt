package com.itpm_gk.android_it_terminology_search_app.util.enum

import com.itpm_gk.android_it_terminology_search_app.R

enum class MojiSize(private val nameResId: Int, private val size: Int) {
    SMALL(R.string.small_str, 12), DEFAULT(R.string.default_str, 16), BIG(R.string.big_str, 20);
    fun getMojiSize() = size
    fun getMojiNameResId() = nameResId
}