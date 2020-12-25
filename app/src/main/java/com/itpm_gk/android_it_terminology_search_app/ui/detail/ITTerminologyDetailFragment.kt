package com.itpm_gk.android_it_terminology_search_app.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.data.database.pojo.ITTerminology
import com.itpm_gk.android_it_terminology_search_app.databinding.FragmentItTerminologyDetailBinding

class ITTerminologyDetailFragment(): Fragment(R.layout.fragment_it_terminology_detail) {

    companion object {
        private val TAG = ITTerminologyDetailFragment::class.simpleName
        private const val KEY_IT_TERMINOLOGY_ID = "key_id"
        private const val KEY_IT_TERMINOLOGY_DISPLAY_NAME = "key_display_name"
        private const val KEY_IT_TERMINOLOGY_DESCRIPTION = "key_description"

        fun newInstance(itTerminology: ITTerminology): ITTerminologyDetailFragment {
            val fragment = ITTerminologyDetailFragment()
            val args = Bundle()
            args.putInt(KEY_IT_TERMINOLOGY_ID, itTerminology.id)
            args.putString(KEY_IT_TERMINOLOGY_DISPLAY_NAME, itTerminology.display_name)
            args.putString(KEY_IT_TERMINOLOGY_DESCRIPTION, itTerminology.description)
            fragment.arguments = args
            return fragment
        }
    }

    private var binding: FragmentItTerminologyDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create DataBinding instance.
        val dataBinding: FragmentItTerminologyDetailBinding? = DataBindingUtil.bind(view)
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

    override fun onDestroy() {
        super.onDestroy()
        binding?.unbind()
    }
}