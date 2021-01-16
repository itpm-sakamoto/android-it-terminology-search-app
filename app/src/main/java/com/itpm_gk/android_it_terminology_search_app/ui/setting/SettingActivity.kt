package com.itpm_gk.android_it_terminology_search_app.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.databinding.ActivitySettingBinding
import com.itpm_gk.android_it_terminology_search_app.util.enum.MojiSize
import com.itpm_gk.android_it_terminology_search_app.util.enum.SharedPreferencesUtil
import com.itpm_gk.android_it_terminology_search_app.util.enum.Theme

class SettingActivity: AppCompatActivity(), SettingFragment.OnSettingActionListener,
    ThemeSettingFragment.OnThemeSettingActionListener {

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
        val mojiSize = SharedPreferencesUtil.getSelectMojiSize(this)
        val fragment = SettingFragment.newInstance(this, getString(mojiSize.getMojiNameResId()))
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
     * テーマ設定画面への遷移処理
     */
    override fun moveToThemeSetting() {
        val selectThemeIndex = SharedPreferencesUtil.getSelectTheme(this)
        val fragment = ThemeSettingFragment.newInstance(selectThemeIndex,this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.setting_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    /**
     * 文字サイズ変更ダイアログの表示
     */
    override fun showMojiSizeDialog() {
        MojiSizeDialogFragment(object : MojiSizeDialogFragment.OnItemClickListener {
            override fun onItemClick(mojiSize: MojiSize) {
                // 表示を更新する
                val fragment = supportFragmentManager.findFragmentById(R.id.setting_fragment_container)
                if (fragment is SettingFragment) {
                    fragment.updateMojiSizeDisplay(getString(mojiSize.getMojiNameResId()))
                }
            }
        }).show(supportFragmentManager, "MojiSizeDialogFragment")
    }

    /**
     * タイトル変更
     */
    override fun titleChange(titleResId: Int) {
        supportActionBar?.title = getString(titleResId)
    }

    /**
     * テーマ選択
     */
    override fun selectTheme(theme: Theme) {
        SharedPreferencesUtil.saveSelectTheme(this, theme)
    }
}