package com.itpm_gk.android_it_terminology_search_app.ui.license

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.databinding.ActivityLicenseBinding

class LicenseActivity: AppCompatActivity(), LicenseFragment.OnLicenseActionListener {

    companion object {
        fun createIntent(context: Context) = Intent(context, LicenseActivity::class.java)
    }

    // DataBindingの保持
    private lateinit var binding: ActivityLicenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_license)

        // ActionBar設定
        actionBarSettings()

        val fragment = LicenseFragment.newInstance(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.license_fragment_container, fragment)
            .commit()
    }

    /**
     * ActionBarの初期設定を行うメソッド
     */
    private fun actionBarSettings() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    /**
     * オプションアイテム選択時の処理を行うメソッド
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var result = true
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            else -> result = super.onOptionsItemSelected(item)
        }
        return result
    }

    /**
     * タイトル変更
     */
    override fun titleChange(titleResId: Int) {
        supportActionBar?.title = getString(titleResId)
    }
}