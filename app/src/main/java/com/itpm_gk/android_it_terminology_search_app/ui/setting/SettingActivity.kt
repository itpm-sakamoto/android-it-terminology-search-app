package com.itpm_gk.android_it_terminology_search_app.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.databinding.ActivitySettingBinding

class SettingActivity: AppCompatActivity(), SettingFragment.OnSettingActionListener {

    companion object {
        fun createIntent(context: Context) = Intent(context, SettingActivity::class.java)
    }

    // DataBindingの保持
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)

        // ActionBar設定
        actionBarSettings()

        // Fragmentの設定
        val fragment = SettingFragment.newInstance(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.setting_fragment_container, fragment)
            .commit()
    }

    /**
     * ActionBarの初期設定を行うメソッド
     */
    private fun actionBarSettings() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.settings)
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
}