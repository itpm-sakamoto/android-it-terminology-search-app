package com.itpm_gk.android_it_terminology_search_app.util.enum

import android.content.Context

class SharedPreferencesUtil {

    companion object {
        private const val APP_MEMORY = "app_memory"
        // 選択中の文字サイズ
        private const val SELECT_MOJI_SIZE = "select_moji_size"
        // 選択中のテーマ
        private const val SELECT_THEME = "select_theme"

        // デフォルト値
        private const val DEFAULT_MOJI_SIZE = 1
        private const val DEFAULT_THEME_ORDINAL = 0

        private fun getSharedPreference(context: Context)
                = context.getSharedPreferences(APP_MEMORY, Context.MODE_PRIVATE)

        private fun getEditor(context: Context) = getSharedPreference(context).edit()

        private fun getInt(context: Context, key: String, defaultValue: Int)
                = getSharedPreference(context).getInt(key, defaultValue)

        private fun setInt(context: Context, key: String, value: Int) {
            getEditor(context).putInt(key, value).apply()
        }

        /**
         * 選択中のテーマを取得
         */
        fun getSelectTheme(context: Context) = getInt(context, SELECT_THEME, DEFAULT_THEME_ORDINAL)

        /**
         * 選択中のテーマを保存
         */
        fun saveSelectTheme(context: Context, theme: Theme) = setInt(context, SELECT_THEME, theme.ordinal)

        /**
         * 選択中の文字サイズを取得
         */
        fun getSelectMojiSize(context: Context): MojiSize {
            return when (getInt(context, SELECT_MOJI_SIZE, DEFAULT_MOJI_SIZE)) {
                MojiSize.SMALL.ordinal -> MojiSize.SMALL
                MojiSize.BIG.ordinal -> MojiSize.BIG
                else -> MojiSize.DEFAULT
            }
        }

        /**
         * 選択中の文字サイズを保存
         */
        fun saveSelectMojiSize(context: Context, mojiSize: MojiSize) {
            setInt(context, SELECT_MOJI_SIZE, mojiSize.ordinal)
        }
    }
}