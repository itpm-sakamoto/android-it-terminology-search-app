package com.itpm_gk.android_it_terminology_search_app.ui.top

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.adapter.ITTerminologyListAdapter
import com.itpm_gk.android_it_terminology_search_app.data.database.entity.ITTerminology
import kotlinx.android.synthetic.main.fragment_it_terminology_top.*

class ITTerminologyTopFragment(private val listener: OnITTerminologyTopActionListener):
    Fragment(R.layout.fragment_it_terminology_top),
    ITTerminologyListAdapter.ITTerminologyListAdapterCallbackListener {

    companion object {
        fun newInstance(listener: OnITTerminologyTopActionListener) = ITTerminologyTopFragment(listener)
    }

    private lateinit var viewModel: ITTerminologyTopViewModel
    private lateinit var adapter: ITTerminologyListAdapter

    interface OnITTerminologyTopActionListener {
        fun moveToDetail(itTerminology: ITTerminology)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModelの作成
        viewModel = createViewModel()

        // Adapterの作成
        adapter = createAdapter()

        // RecyclerViewの初期設定
        initRecyclerView(adapter)

        viewModel.itTerminologyList.observe(viewLifecycleOwner, Observer {
            adapter.notifyDataSetChanged()
        })
        viewLifecycleOwner.lifecycle.addObserver(viewModel)
    }

    /**
     * ViewModelの作成
     */
    private fun createViewModel() =
        ViewModelProvider(
            this,
            ITTerminologyTopViewModelFactory(lifecycleScope, requireContext())
        ).get(ITTerminologyTopViewModel::class.java)

    /**
     * Adapterの作成
     */
    private fun createAdapter(): ITTerminologyListAdapter {
        val itTerminologySnapshot = viewModel.itTerminologyList.value
            ?: ArrayList<ITTerminology>().also {
                viewModel.itTerminologyList.value = it
            }
        // Adapterの生成
        val adapter = ITTerminologyListAdapter(
            layoutInflater,
            itTerminologySnapshot
        )
        // タップイベントの設定
        adapter.setOnClickListener(this)
        return adapter
    }

    /**
     * RecyclerViewの初期設定
     */
    private fun initRecyclerView(adapter: ITTerminologyListAdapter) {
        val layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        // RecyclerViewへの設定
        recycler_view.also {recyclerView ->
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = layoutManager
            recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), layoutManager.orientation))
            recyclerView.adapter = adapter
        }
    }

    /**
     * リストのタップイベント
     */
    override fun onItemSelected(itTerminology: ITTerminology) {
        // Activityへのコールバック
        listener.moveToDetail(itTerminology)
    }
}