package com.itpm_gk.android_it_terminology_search_app.ui.word_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.data.database.entity.ITTerminology
import com.itpm_gk.android_it_terminology_search_app.databinding.ActivityWordDetailBinding

class WordDetailActivity: AppCompatActivity(), WordDetailFragment.OnWordDetailActionListener {

    companion object {
        private val TAG = WordDetailActivity::class.java.simpleName
        private const val EXTRA_IT_TERMINOLOGY = "extra_it_terminology"
        fun createIntent(context: Context, itTerminology: ITTerminology): Intent {
            val intent = Intent(context, WordDetailActivity::class.java)
            intent.putExtra(EXTRA_IT_TERMINOLOGY, itTerminology)
            return intent
        }
    }

    private lateinit var binding: ActivityWordDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_word_detail)

        setSupportActionBar(binding.toolbar)

        val itTerminology = intent.getParcelableExtra<ITTerminology>(EXTRA_IT_TERMINOLOGY) ?: run {
            Log.d(TAG, "it terminology data is not found.")
            return
        }
        val fragment = WordDetailFragment.newInstance(itTerminology, this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.word_detail_fragment_container, fragment)
            .commit()
    }

    override fun titleChange(titleResId: Int) {
        supportActionBar?.title = getString(titleResId)
    }

    override fun actionBarSetting(className: String) {
        when (className) {
            WordDetailFragment::class.java.simpleName -> {
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