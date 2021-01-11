package com.itpm_gk.android_it_terminology_search_app.ui.setting

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.databinding.FragmentSettingBinding

class SettingFragment(private val listener: OnSettingActionListener): Fragment(R.layout.fragment_setting), View.OnClickListener {

    companion object {
        fun newInstance(listener: OnSettingActionListener) = SettingFragment(listener)
    }

    interface OnSettingActionListener {
        fun moveToThemeSetting()
        fun showMojiSizeDialog()
        fun titleChange(titleResId: Int)
    }

    // DataBindingの保持
    private var binding: FragmentSettingBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)
        binding?.let {
            it.theme.setOnClickListener(this)
            it.mojiSize.setOnClickListener(this)
        }
    }

    override fun onResume() {
        super.onResume()
        listener.titleChange(R.string.settings)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.theme -> listener.moveToThemeSetting()
            R.id.moji_size -> listener.showMojiSizeDialog()
            else -> TODO("Not yet implemented")
        }
    }
}
