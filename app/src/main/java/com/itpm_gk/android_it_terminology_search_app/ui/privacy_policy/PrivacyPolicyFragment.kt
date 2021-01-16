package com.itpm_gk.android_it_terminology_search_app.ui.privacy_policy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itpm_gk.android_it_terminology_search_app.R

class PrivacyPolicyFragment(private val listener: OnPrivacyPolicyActionListener): Fragment() {

    companion object {
        fun newInstance(listener: OnPrivacyPolicyActionListener) = PrivacyPolicyFragment(listener)
    }

    interface OnPrivacyPolicyActionListener {
        fun titleChange(titleResId: Int)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        listener.titleChange(R.string.privacy_policy)
    }
}