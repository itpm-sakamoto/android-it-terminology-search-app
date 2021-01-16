package com.itpm_gk.android_it_terminology_search_app.ui.about_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.databinding.ActivityAboutAppBinding
import com.itpm_gk.android_it_terminology_search_app.ui.license.LicenseActivity
import com.itpm_gk.android_it_terminology_search_app.ui.privacy_policy.PrivacyPolicyActivity

class AboutAppActivity: AppCompatActivity(), AboutAppFragment.OnAboutAppActionListener {

    companion object {
        fun createIntent(context: Context) = Intent(context, AboutAppActivity::class.java)
    }

    // DataBindingの保持
    private lateinit var binding: ActivityAboutAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_app)

        // ActionBarの設定
        actionBarSettings()

        // Fragmentの設定
        val fragment = AboutAppFragment.newInstance(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.about_app_fragment_container, fragment)
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
     * ライセンス画面への遷移
     */
    override fun moveToLicense() {
        startActivity(LicenseActivity.createIntent(this))
    }

    /**
     * プライバシーポリシー画面への遷移
     */
    override fun moveToPrivacyPolicy() {
        startActivity(PrivacyPolicyActivity.createIntent(this))
    }

    /**
     * タイトル変更
     */
    override fun titleChange(titleResId: Int) {
        supportActionBar?.title = getString(titleResId)
    }
}