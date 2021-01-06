package com.itpm_gk.android_it_terminology_search_app.ui.top

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.adapter.QualificationListAdapter
import com.itpm_gk.android_it_terminology_search_app.data.database.entity.Qualification
import com.itpm_gk.android_it_terminology_search_app.databinding.FragmentQualificationListBinding

class QualificationListFragment: Fragment(R.layout.fragment_qualification_list) {

    companion object {
        fun newInstance() = QualificationListFragment()
    }

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: QualificationListAdapter
    private var binding: FragmentQualificationListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModelの作成
        val qualificationListSnapshot = ArrayList<Qualification>()
        qualificationListSnapshot.add(Qualification(1, "", "MTA 98-367", "セキュリティの基礎", "A4CBFA", 30))
        qualificationListSnapshot.add(Qualification(2, "", "MTA 98-366", "ネットワークの基礎", "C79CE8",30))
        qualificationListSnapshot.add(Qualification(3, "", "MTA 98-349", "オペレーティングシステムに関する基本事項", "9ACAAF", 30))
        qualificationListSnapshot.add(Qualification(4, "", "タイトル4", "", "A4CBFA", 30))
        qualificationListSnapshot.add(Qualification(5, "", "タイトル5", "", "A4CBFA", 30))

        // Adapterの作成
        adapter = QualificationListAdapter(
            layoutInflater,
            qualificationListSnapshot
        )

        // LayoutManagerの作成
        layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        val dataBinding: FragmentQualificationListBinding? = DataBindingUtil.bind(view)
        binding = dataBinding ?: return

        dataBinding.recyclerView.also {
            it.setHasFixedSize(true)
            it.layoutManager = layoutManager
            it.adapter = adapter
        }

        adapter.notifyDataSetChanged()
        showQualificationList()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.unbind()
    }

    private fun showQualificationList() {
        binding?.also {
            it.recyclerView.visibility = View.VISIBLE
            it.emptyText.visibility = View.GONE
        }
    }
}