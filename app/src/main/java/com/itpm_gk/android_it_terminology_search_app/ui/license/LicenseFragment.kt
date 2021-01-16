package com.itpm_gk.android_it_terminology_search_app.ui.license

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itpm_gk.android_it_terminology_search_app.R

class LicenseFragment(private val listener: OnLicenseActionListener): Fragment() {

    companion object {
        fun newInstance(listener: OnLicenseActionListener) = LicenseFragment(listener)
    }

    interface OnLicenseActionListener {
        fun titleChange(titleResId: Int)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        listener.titleChange(R.string.license)
    }
}
