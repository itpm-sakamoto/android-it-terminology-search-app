package com.itpm_gk.android_it_terminology_search_app.ui.word_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.data.database.entity.ITTerminology
import com.itpm_gk.android_it_terminology_search_app.data.database.entity.Qualification
import com.itpm_gk.android_it_terminology_search_app.ui.word_detail.WordDetailActivity
import kotlinx.android.synthetic.main.app_bar_top.*

class WordListActivity: AppCompatActivity(),
    WordListFragment.OnITTerminologyTopActionListener {

    companion object {
        private const val EXTRA_QUALIFICATION = "extra_qualification"
        fun createIntent(context: Context, qualification: Qualification): Intent {
            val intent = Intent(context, WordListActivity::class.java)
            intent.putExtra(EXTRA_QUALIFICATION, qualification)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)

        setSupportActionBar(toolbar)

        val fragment =
            WordListFragment.newInstance(
                this
            )

        supportFragmentManager.beginTransaction()
            .replace(R.id.it_terminology_top_fragment_container, fragment)
            .commitNow()
    }

    /**
     * 詳細画面への遷移
     */
    override fun moveToDetail(itTerminology: ITTerminology) {
        // 詳細画面へ遷移
        val intent = WordDetailActivity.createIntent(this, itTerminology)
        startActivity(intent)
    }

    /**
     * タイトル変更
     */
    override fun titleChange(titleResId: Int) {
        supportActionBar?.title = getString(titleResId)
    }

    /**
     * ActionBar設定
     */
    override fun actionBarSetting(className: String) {
        when (className) {
            WordListFragment::class.java.simpleName -> {
                supportActionBar?.also {
                    it.setDisplayHomeAsUpEnabled(true)
                    it.setHomeButtonEnabled(true)
                }
            }
            else -> return
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