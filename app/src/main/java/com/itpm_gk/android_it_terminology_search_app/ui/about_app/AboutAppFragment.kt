package com.itpm_gk.android_it_terminology_search_app.ui.about_app

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.databinding.FragmentAboutAppBinding
import com.itpm_gk.android_it_terminology_search_app.ui.license.LicenseActivity

class AboutAppFragment(private val listener: OnAboutAppActionListener): Fragment(R.layout.fragment_about_app),
    View.OnClickListener {

    companion object {
        fun newInstance(listener: OnAboutAppActionListener) = AboutAppFragment(listener)
    }

    private var binding: FragmentAboutAppBinding? = null

    interface OnAboutAppActionListener {
        fun moveToLicense()
        fun moveToPrivacyPolicy()
        fun titleChange(titleResId: Int)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)

        binding?.also {
            it.license.setOnClickListener(this)
            it.privacyPolicy.setOnClickListener(this)
        }
    }

    override fun onResume() {
        super.onResume()
        listener.titleChange(R.string.about_app)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.license -> listener.moveToLicense()
            R.id.privacy_policy -> listener.moveToPrivacyPolicy()
            else -> {}
        }
    }
}