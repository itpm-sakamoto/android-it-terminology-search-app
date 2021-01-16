package com.itpm_gk.android_it_terminology_search_app.ui.setting

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.databinding.FragmentSettingBinding
import com.itpm_gk.android_it_terminology_search_app.util.enum.MojiSize

class SettingFragment(private val listener: OnSettingActionListener): Fragment(R.layout.fragment_setting), View.OnClickListener {

    companion object {
        private const val KEY_MOJI_SIZE_STR = "key_moji_size_str"
        fun newInstance(listener: OnSettingActionListener, mojiSizeStr: String): SettingFragment {
            val args = Bundle()
            args.putString(KEY_MOJI_SIZE_STR, mojiSizeStr)
            val fragment = SettingFragment(listener)
            fragment.arguments = args
            return fragment
        }
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
        val mojiSizeStr = arguments?.getString(KEY_MOJI_SIZE_STR) ?: getString(R.string.default_str)
        binding = DataBindingUtil.bind(view)
        binding?.also {
            it.mojiSizeText.text = mojiSizeStr
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

    /**
     * 文字サイズのサブタイトルを変更するメソッド
     */
    fun updateMojiSizeDisplay(mojiSizeStr: String) {
        binding?.mojiSizeText?.text = mojiSizeStr
    }
}
