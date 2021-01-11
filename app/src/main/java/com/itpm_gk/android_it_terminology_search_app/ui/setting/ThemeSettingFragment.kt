package com.itpm_gk.android_it_terminology_search_app.ui.setting

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Point
import android.graphics.PorterDuff
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.databinding.FragmentThemeBinding
import com.itpm_gk.android_it_terminology_search_app.databinding.ListItemThemeBinding
import com.itpm_gk.android_it_terminology_search_app.util.enum.Theme

class ThemeSettingFragment(private val listener: OnThemeSettingActionListener):
    Fragment(R.layout.fragment_theme) {

    companion object {
        // クラス名（タグ用）
        private val TAG = ThemeSettingFragment::class.simpleName
        private const val ARGS_THEME_FRAGMENT_THEME_ORDINAL = "args_theme_fragment_theme_ordinal"
        private const val MARGIN_SIZE = 48
        private const val WAITING_TIME = 300

        fun newInstance(selectThemeIndex: Int, listener: OnThemeSettingActionListener): ThemeSettingFragment {
            val fragment = ThemeSettingFragment(listener)
            val args = Bundle()
            args.putInt(ARGS_THEME_FRAGMENT_THEME_ORDINAL, selectThemeIndex)
            fragment.arguments = args
            return fragment
        }

        /**
         * This method gets the size of the display.
         *
         * @param activity Activity context.
         * @return point Point.
         */
        private fun getDisplaySize(activity: Activity): Point {
            val display = activity.windowManager.defaultDisplay
            val point = Point()
            display.getSize(point)
            return point;
        }
    }

    interface OnThemeSettingActionListener {
        fun titleChange(titleResId: Int)
        fun selectTheme(theme: Theme)
    }

    private lateinit var binding: FragmentThemeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectIndex = arguments?.getInt(ARGS_THEME_FRAGMENT_THEME_ORDINAL) ?: run {
            Log.e(TAG, "error: arguments data not found.")
            return
        }
        // 選択中のテーマを取得
        val selectTheme = Theme.values()[selectIndex]

        binding = DataBindingUtil.bind(view) ?: run {
            Log.e(TAG, "error: Failed to get the binding.")
            return
        }

        val displaySize = getDisplaySize(requireActivity())
        val maxWidth = displaySize.x / 2
        val viewSize = maxWidth - (MARGIN_SIZE * 2)

        // Adapterの作成
        val adapter = ThemeListAdapter(
            layoutInflater,
            viewSize,
            requireContext(),
            selectTheme
        )
        adapter.setOnClickListener(object : ThemeListAdapter.ThemeListAdapterCallbackListener {
            override fun onItemSelected(theme: Theme) {
                listener.selectTheme(theme)
            }
        })
        adapter.setHasStableIds(true)

        binding.recyclerView.also {
            it.layoutManager = GridLayoutManager(requireContext(), 2)
            it.addItemDecoration(ItemOffsetDecoration(MARGIN_SIZE))
            it.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        listener.titleChange(R.string.theme)
    }

    private class ThemeListAdapter(
        private val layoutInflater: LayoutInflater,
        private val viewSize: Int,
        private val context: Context,
        private var selectTheme: Theme
    ): RecyclerView.Adapter<ThemeListAdapter.ThemeViewHolder>() {

        interface ThemeListAdapterCallbackListener {
            fun onItemSelected(theme: Theme)
        }

        private var callbackListener: ThemeListAdapterCallbackListener? = null
        // 最後にタップした時間の保持
        private var lastClickTime = 0L

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
            val binding = DataBindingUtil.inflate<ListItemThemeBinding>(
                layoutInflater,
                R.layout.list_item_theme,
                parent,
                false
            )
            binding.apply {
                cardView.layoutParams.height = viewSize
                val statusBarSize = viewSize / 3 / 3
                statusBar.layoutParams.height = statusBarSize
                headerBar.layoutParams.height = statusBarSize * 2
                fab.layoutParams.height = statusBarSize * 2
                fab.layoutParams.width = statusBarSize * 2
            }
            return ThemeViewHolder(binding, selectTheme)
        }

        override fun getItemCount() = Theme.values().size

        override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
            val themeItem = Theme.values()[position]
            val binding = DataBindingUtil.findBinding<ListItemThemeBinding>(holder.itemView)
            holder.itemView.setOnClickListener {
                // 連続タップを抑止する為の処理
                if (WAITING_TIME > (System.currentTimeMillis() - lastClickTime)) {
                    Log.i(TAG, "Since the click feeling is short, the process is not executed.")
                    return@setOnClickListener
                }
                lastClickTime = System.currentTimeMillis()
                callbackListener?.onItemSelected(themeItem)
                selectTheme = themeItem
                notifyDataSetChanged()
            }
            binding?.apply {
                shadow.visibility = if (themeItem == selectTheme) View.VISIBLE else View.GONE
                checkImg.visibility = if (themeItem == selectTheme) View.VISIBLE else View.GONE
            }
            holder.bind(context, themeItem)
        }

        fun setOnClickListener(listener: ThemeListAdapterCallbackListener) {
            callbackListener = listener
        }

        class ThemeViewHolder(private val binding: ListItemThemeBinding, val selectTheme: Theme): RecyclerView.ViewHolder(binding.root) {
            fun bind(context: Context, themeItem: Theme) {
                binding.apply {
                    statusBar.setBackgroundResource(themeItem.statusBarColor)
                    headerBar.setBackgroundResource(themeItem.headerBarColor)
                    cardView.setCardBackgroundColor(ContextCompat.getColor(context, themeItem.themeBgColor))
                    fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, themeItem.fabColor))
                    checkImg.setColorFilter(ContextCompat.getColor(context, themeItem.headerBarColor), PorterDuff.Mode.SRC_ATOP)
                }
            }
        }
    }

    private class ItemOffsetDecoration(private val offset: Int): RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.set(offset, offset, offset, offset)
        }
    }
}