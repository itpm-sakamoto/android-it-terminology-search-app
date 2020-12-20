package com.itpm_gk.android_it_terminology_search_app.ui.top

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itpm_gk.android_it_terminology_search_app.R
import kotlinx.android.synthetic.main.app_bar_top.*

class ITTerminologyTopActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_it_terminology_top)

        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        val fragment = ITTerminologyTopFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .replace(R.id.it_terminology_top_fragment_container, fragment)
            .commitNow()
    }
}