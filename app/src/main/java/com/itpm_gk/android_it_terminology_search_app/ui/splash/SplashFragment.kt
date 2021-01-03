package com.itpm_gk.android_it_terminology_search_app.ui.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import com.itpm_gk.android_it_terminology_search_app.R

class SplashFragment: Fragment(R.layout.fragment_splash) {

    interface SplashActionListener {
        fun moveToTop()
    }

    private lateinit var actionListener: SplashActionListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            actionListener = context as SplashActionListener
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed(Runnable {
            actionListener.moveToTop()
        }, 2000)
    }
}