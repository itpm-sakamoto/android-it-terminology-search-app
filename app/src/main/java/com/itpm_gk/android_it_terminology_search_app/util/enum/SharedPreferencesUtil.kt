package com.itpm_gk.android_it_terminology_search_app.util.enum

import android.content.Context

class SharedPreferencesUtil {

    companion object {
        private const val APP_MEMORY = "app_memory"
        // 選択中のテーマ
        private const val SELECT_THEME = "select_theme"

        // デフォルト値
        private const val DEFAULT_THEME_ORDINAL = 0

        private fun getSharedPreference(context: Context)
                = context.getSharedPreferences(APP_MEMORY, Context.MODE_PRIVATE)

        private fun getEditor(context: Context) = getSharedPreference(context).edit()

        private fun getInt(context: Context, key: String, defaultValue: Int)
                = getSharedPreference(context).getInt(key, defaultValue)

        private fun setInt(context: Context, key: String, value: Int) {
            getEditor(context).putInt(key, value).commit()
        }

        /**
         * 選択中のテーマを取得
         */
        fun getSelectTheme(context: Context) = getInt(context, SELECT_THEME, DEFAULT_THEME_ORDINAL)

        /**
         * 選択中のテーマを保存
         */
        fun saveSelectTheme(context: Context, theme: Theme) = setInt(context, SELECT_THEME, theme.ordinal)
    }
}