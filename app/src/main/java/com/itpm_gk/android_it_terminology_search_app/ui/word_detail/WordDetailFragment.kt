package com.itpm_gk.android_it_terminology_search_app.ui.word_detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.data.database.entity.ITTerminology
import com.itpm_gk.android_it_terminology_search_app.databinding.FragmentWordDetailBinding

class WordDetailFragment(private val listener: OnWordDetailActionListener): Fragment(R.layout.fragment_word_detail) {

    companion object {
        private val TAG = WordDetailFragment::class.simpleName
        private const val KEY_IT_TERMINOLOGY_ID = "key_id"
        private const val KEY_IT_TERMINOLOGY_DISPLAY_NAME = "key_display_name"
        private const val KEY_IT_TERMINOLOGY_DESCRIPTION = "key_description"

        fun newInstance(itTerminology: ITTerminology, listener: OnWordDetailActionListener): WordDetailFragment {
            val fragment = WordDetailFragment(listener)
            val args = Bundle()
            args.putInt(KEY_IT_TERMINOLOGY_ID, itTerminology.id)
            args.putString(KEY_IT_TERMINOLOGY_DISPLAY_NAME, itTerminology.display_name)
            args.putString(KEY_IT_TERMINOLOGY_DESCRIPTION, itTerminology.description)
            fragment.arguments = args
            return fragment
        }
    }

    interface OnWordDetailActionListener {
        fun titleChange(titleResId: Int)
        fun actionBarSetting(className: String)
    }

    private var binding: FragmentWordDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create DataBinding instance.
        val dataBinding: FragmentWordDetailBinding? = DataBindingUtil.bind(view)
        binding = dataBinding ?: run {
            Log.w(TAG, "DataBinding was not found.")
            return
        }

        val id = arguments?.getInt(KEY_IT_TERMINOLOGY_ID) ?: run {
            Log.w(TAG, "it terminology id was not found.")
            return
        }
        val displayName = arguments?.getString(KEY_IT_TERMINOLOGY_DISPLAY_NAME) ?: run {
            Log.w(TAG, "it terminology display name was not found.")
            return
        }
        val description = arguments?.getString(KEY_IT_TERMINOLOGY_DESCRIPTION) ?: run {
            Log.w(TAG, "it terminology description name was not found.")
            return
        }
        val itTerminology = ITTerminology(id, "", displayName, description)
        binding?.itTerminology = itTerminology
    }

    override fun onResume() {
        super.onResume()
        listener.titleChange(R.string.word_detail)
        listener.actionBarSetting(WordDetailFragment::class.java.simpleName)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.unbind()
    }
}