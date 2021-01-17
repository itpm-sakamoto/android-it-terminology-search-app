package com.itpm_gk.android_it_terminology_search_app.ui.word_list

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.itpm_gk.android_it_terminology_search_app.data.database.ITTerminologyRoomDatabase
import com.itpm_gk.android_it_terminology_search_app.data.database.entity.ITTerminology
import com.itpm_gk.android_it_terminology_search_app.data.repository.ITTerminologyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordListViewModel(
    private val coroutineScope: CoroutineScope,
    application: Application
): AndroidViewModel(application), LifecycleObserver {

    companion object {
        private val TAG = WordListViewModel::class.simpleName
    }

    private val itTerminologyRepository: ITTerminologyRepository
    private val context: Context

    val itTerminologyList = MutableLiveData<ArrayList<ITTerminology>>()

    init {
        val itTerminologyDao = ITTerminologyRoomDatabase.getDatabase(application).itTerminologyDao()
        itTerminologyRepository = ITTerminologyRepository(itTerminologyDao)
        context = application
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d(TAG, "onCreate called.")
        // データ読み込み処理
        coroutineScope.launch {
            itTerminologyRepository.insert(ITTerminology(1, "api", "API", "あるコンピュータプログラム（ソフトウェア）の機能や管理するデータなどを、外部の他のプログラムから呼び出して利用するための手順やデータ形式などを定めた規約のこと。"))
            itTerminologyRepository.insert(ITTerminology(2, "interface", "インターフェース", "二つのものが接続・接触する箇所や、両者の間で情報や信号などをやりとりするための手順や規約を定めたものを意味する。"))
            itTerminologyRepository.insert(ITTerminology(3, "ethernet", "イーサネット", "主に室内や建物内でコンピュータや電子機器をケーブルで繋いで通信する有線LAN（構内ネットワーク）の標準の一つで、最も普及している規格。"))
            itTerminologyRepository.insert(ITTerminology(4, "instance", "インスタンス", "ソフトウェアの分野では、あらかじめ定義されたコンピュータプログラムやデータ構造などを、メインメモリ上に展開して処理・実行できる状態にしたものを指す。"))
            itTerminologyRepository.insert(ITTerminology(5, "module", "モジュール", "機器やシステムの一部を構成するひとまとまりの機能を持った部品で、システム中核部や他の部品への接合部（インターフェース）の仕様が明確に定義され、容易に追加や交換ができるようなもののことを指す。"))
        }

        loadData()
    }

    /**
     * IT用語の表示をする為のデータ取得メソッド
     */
    private fun loadData() {
        coroutineScope.launch(Dispatchers.IO) {
            val itTerminologySnapshot = itTerminologyList.value ?: ArrayList()
            itTerminologySnapshot.clear()
            itTerminologySnapshot.addAll(itTerminologyRepository.loadAll())
            itTerminologyList.postValue(itTerminologySnapshot)
        }
    }
}