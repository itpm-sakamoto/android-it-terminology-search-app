package com.itpm_gk.android_it_terminology_search_app.ui.top

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.adapter.ITTerminologyListAdapter
import com.itpm_gk.android_it_terminology_search_app.data.database.pojo.ITTerminology
import kotlinx.android.synthetic.main.fragment_it_terminology_top.*

class ITTerminologyTopFragment(private val listener: OnITTerminologyTopActionListener):
    Fragment(R.layout.fragment_it_terminology_top),
    ITTerminologyListAdapter.ITTerminologyListAdapterCallbackListener {

    companion object {
        fun newInstance(listener: OnITTerminologyTopActionListener) = ITTerminologyTopFragment(listener)
    }

    interface OnITTerminologyTopActionListener {
        fun moveToDetail(itTerminology: ITTerminology)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // サンプルデータ
        val datas: List<ITTerminology> = mutableListOf(
            ITTerminology(1, "api", "API", "あるコンピュータプログラム（ソフトウェア）の機能や管理するデータなどを、外部の他のプログラムから呼び出して利用するための手順やデータ形式などを定めた規約のこと。"),
            ITTerminology(2, "interface", "インターフェース", "二つのものが接続・接触する箇所や、両者の間で情報や信号などをやりとりするための手順や規約を定めたものを意味する。"),
            ITTerminology(3, "ethernet", "イーサネット", "主に室内や建物内でコンピュータや電子機器をケーブルで繋いで通信する有線LAN（構内ネットワーク）の標準の一つで、最も普及している規格。"),
            ITTerminology(4, "instance", "インスタンス", "ソフトウェアの分野では、あらかじめ定義されたコンピュータプログラムやデータ構造などを、メインメモリ上に展開して処理・実行できる状態にしたものを指す。"),
            ITTerminology(5, "module", "モジュール", "機器やシステムの一部を構成するひとまとまりの機能を持った部品で、システム中核部や他の部品への接合部（インターフェース）の仕様が明確に定義され、容易に追加や交換ができるようなもののことを指す。")
        )
        // Adapterの生成
        val adapter = ITTerminologyListAdapter(
            layoutInflater,
            datas
        )
        // タップイベントの設定
        adapter.setOnClickListener(this)
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