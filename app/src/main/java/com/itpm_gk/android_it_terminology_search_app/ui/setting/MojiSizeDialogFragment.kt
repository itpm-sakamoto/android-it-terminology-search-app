package com.itpm_gk.android_it_terminology_search_app.ui.setting

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.databinding.LayoutMojiSizeDialogBinding
import com.itpm_gk.android_it_terminology_search_app.util.enum.MojiSize
import com.itpm_gk.android_it_terminology_search_app.util.enum.SharedPreferencesUtil

class MojiSizeDialogFragment(private val listener: OnItemClickListener): DialogFragment(), View.OnClickListener {

    companion object {
        private val TAG = MojiSizeDialogFragment::class.simpleName
        val MOJI_SIZE_IMG_VIEW_ID = listOf(
            R.id.select_moji_size_img_num_0,
            R.id.select_moji_size_img_num_1,
            R.id.select_moji_size_img_num_2
        )
    }

    interface OnItemClickListener {
        fun onItemClick(mojiSize: MojiSize)
    }

    private lateinit var binding: LayoutMojiSizeDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            binding = DataBindingUtil.inflate<LayoutMojiSizeDialogBinding>(
                layoutInflater, R.layout.layout_moji_size_dialog, null, false)
            val mojiSize = SharedPreferencesUtil.getSelectMojiSize(it)
            for ((index, resId) in MOJI_SIZE_IMG_VIEW_ID.withIndex()) {
                val mojiSizeImg = when (index) {
                    1 -> binding.selectMojiSizeImgNum1
                    2 -> binding.selectMojiSizeImgNum2
                    else -> binding.selectMojiSizeImgNum0
                }
                mojiSizeImg.visibility = if (mojiSize.ordinal == index) View.VISIBLE else View.INVISIBLE
            }
            binding.mojiSizeSettingNum0.setOnClickListener(this)
            binding.mojiSizeSettingNum1.setOnClickListener(this)
            binding.mojiSizeSettingNum2.setOnClickListener(this)
            binding.closeButton.setOnClickListener(this)
            val builder = AlertDialog.Builder(it, R.style.AlertDialogTheme)
                .setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null.")
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.close_button) {
            dialog?.dismiss()
            return
        }
        binding.selectMojiSizeImgNum0.visibility = View.INVISIBLE
        binding.selectMojiSizeImgNum1.visibility = View.INVISIBLE
        binding.selectMojiSizeImgNum2.visibility = View.INVISIBLE
        var mojiSize = when (view?.id) {
            R.id.moji_size_setting_num_0 -> {
                binding.selectMojiSizeImgNum0.visibility = View.VISIBLE
                MojiSize.SMALL
            }
            R.id.moji_size_setting_num_1 -> {
                binding.selectMojiSizeImgNum1.visibility = View.VISIBLE
                MojiSize.DEFAULT
            }
            R.id.moji_size_setting_num_2 -> {
                binding.selectMojiSizeImgNum2.visibility = View.VISIBLE
                MojiSize.BIG
            }
            else -> {
                Log.e(TAG, "error: Unknown value.")
                return
            }
        }
        // 選択された項目をコールバックで返す
        listener.onItemClick(mojiSize)
        // 選択された文字の保存
        SharedPreferencesUtil.saveSelectMojiSize(requireContext(), mojiSize)
        dialog?.dismiss()
    }
}