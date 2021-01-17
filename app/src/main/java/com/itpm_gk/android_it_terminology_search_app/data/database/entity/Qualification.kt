package com.itpm_gk.android_it_terminology_search_app.data.database.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Qualification(
    val id: Int,
    val image_url: String,
    val title: String,
    val subtitle: String,
    val cardBottomColor: String,
    val total_word_count: Int
) : Parcelable