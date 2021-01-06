package com.itpm_gk.android_it_terminology_search_app.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.data.database.entity.Qualification
import com.itpm_gk.android_it_terminology_search_app.databinding.ListItemQualificationBinding

class QualificationListAdapter(
    private val layoutInflater: LayoutInflater,
    private val qualificationList: List<Qualification>
): RecyclerView.Adapter<QualificationListAdapter.ViewHolder>() {

    interface QualificationListAdapterCallbackListener {
        fun onItemSelected(qualification: Qualification)
    }

    private var callbackListener: QualificationListAdapterCallbackListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ListItemQualificationBinding>(
            layoutInflater,
            R.layout.list_item_qualification,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = qualificationList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = qualificationList[position]
        Log.d("TAG", "item : " + item.title)
        val binding = DataBindingUtil.findBinding<ListItemQualificationBinding>(holder.itemView)
        holder.itemView.setOnClickListener {
            callbackListener?.onItemSelected(item)
        }
        holder.bind(item)
    }

    fun setOnClickListener(callbackListener: QualificationListAdapterCallbackListener) {
        this.callbackListener = callbackListener
    }

    class ViewHolder(private val binding: ListItemQualificationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(qualification: Qualification) {
            binding.titleTextView.text = qualification.title
            binding.subTitleTextView.text = qualification.subtitle
            binding.cardBottom.setBackgroundColor(Color.parseColor("#" + qualification.cardBottomColor))
        }
    }
}