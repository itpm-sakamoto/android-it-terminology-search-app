package com.itpm_gk.android_it_terminology_search_app.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.data.database.entity.ITTerminology
import com.itpm_gk.android_it_terminology_search_app.ui.top.ITTerminologyTopFragment
import com.itpm_gk.android_it_terminology_search_app.ui.splash.SplashFragment
import com.itpm_gk.android_it_terminology_search_app.ui.top.TopActivity

class SplashActivity: AppCompatActivity(), SplashFragment.SplashActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container,
                SplashFragment()
            )
            .commit()
    }

    /**
     * This method moves to the qualification list screen.
     */
    override fun moveToTop() {
        startActivity(TopActivity.createIntent(this))
        finish()
    }
}