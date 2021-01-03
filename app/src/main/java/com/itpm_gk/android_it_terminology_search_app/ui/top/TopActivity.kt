package com.itpm_gk.android_it_terminology_search_app.ui.top

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.databinding.ActivityTopBinding

class TopActivity: AppCompatActivity() {

    companion object {
        fun createIntent(context: Context) = Intent(context, TopActivity::class.java)
    }

    private lateinit var binding: ActivityTopBinding
    private lateinit var fragment: QualificationListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_top)

        setSupportActionBar(binding.appBarTop.toolbar)
        supportActionBar?.title = getString(R.string.qualification_list)

        fragment = QualificationListFragment()

        val toggle = object : ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarTop.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerOpened(drawerView: View) {
                // ドロワーを開いた時の処理
                super.onDrawerOpened(drawerView)
            }
        }

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.apply {
            isDrawerIndicatorEnabled = false
            setToolbarNavigationClickListener { binding.drawerLayout.openDrawer(GravityCompat.START) }
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.top_fragment_container, fragment)
            .commit()
    }
}