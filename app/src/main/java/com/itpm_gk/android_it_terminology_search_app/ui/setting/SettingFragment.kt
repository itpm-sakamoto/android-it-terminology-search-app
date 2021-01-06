package com.itpm_gk.android_it_terminology_search_app.ui.setting

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.databinding.FragmentSettingBinding

class SettingFragment(private val listener: OnSettingActionListener): Fragment(R.layout.fragment_setting) {

    companion object {
        fun newInstance(listener: OnSettingActionListener) = SettingFragment(listener)
    }

    interface OnSettingActionListener {

    }

    // DataBindingの保持
    private var binding: FragmentSettingBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)
    }
}