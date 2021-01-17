package com.itpm_gk.android_it_terminology_search_app.ui.top

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.data.database.entity.Qualification
import com.itpm_gk.android_it_terminology_search_app.databinding.ActivityTopBinding
import com.itpm_gk.android_it_terminology_search_app.ui.about_app.AboutAppActivity
import com.itpm_gk.android_it_terminology_search_app.ui.setting.SettingActivity
import com.itpm_gk.android_it_terminology_search_app.ui.word_list.WordListActivity
import kotlinx.coroutines.delay

class TopActivity: AppCompatActivity(), View.OnClickListener, QualificationListFragment.OnQualificationListActionListener {

    companion object {
        // クラス名（タグ用）
        private val TAG = TopActivity::class.simpleName
        // 連続タップ防止用待ち時間
        private const val WAITING_TIME = 1000L

        fun createIntent(context: Context) = Intent(context, TopActivity::class.java)
    }

    // DataBindingの保持
    private lateinit var binding: ActivityTopBinding
    // 資格一覧画面用Fragmentの保持
    private lateinit var fragment: QualificationListFragment
    // 最後にタップした時間の保持
    private var lastClickTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_top)

        setSupportActionBar(binding.appBarTop.toolbar)
        supportActionBar?.title = getString(R.string.qualification_list)

        fragment = QualificationListFragment.newInstance(this)

        // ドロワーメニューの設定
        initDrawerMenu()

        supportFragmentManager.beginTransaction()
            .replace(R.id.top_fragment_container, fragment)
            .commit()
    }

    private fun initDrawerMenu() {
        val toggle = object : ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarTop.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }
        }
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.apply {
            isDrawerIndicatorEnabled = false
            setToolbarNavigationClickListener { binding.drawerLayout.openDrawer(GravityCompat.START) }
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        binding.navSettings.setOnClickListener(this)
        binding.navAboutApp.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        // 連続タップを抑止する為の処理
        if (WAITING_TIME > (System.currentTimeMillis() - lastClickTime)) {
            Log.i(TAG, "Since the click feeling is short, the process is not executed.")
            return
        }
        lastClickTime = System.currentTimeMillis()

        when (view?.id) {
            // 「設定」が選択された場合
            R.id.nav_settings -> {
                startActivity(SettingActivity.createIntent(this@TopActivity))
                binding.drawerLayout.closeDrawers()
            }
            // 「このアプリについて」が選択された場合
            R.id.nav_about_app -> {
                startActivity(AboutAppActivity.createIntent(this@TopActivity))
                binding.drawerLayout.closeDrawers()
            }
            else -> return
        }
    }

    /**
     * 用語一覧画面への遷移処理
     */
    override fun moveToWordList(qualification: Qualification) {
        startActivity(WordListActivity.createIntent(this, qualification))
    }
}