package com.itpm_gk.android_it_terminology_search_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.itpm_gk.android_it_terminology_search_app.R
import com.itpm_gk.android_it_terminology_search_app.data.database.entity.ITTerminology
import com.itpm_gk.android_it_terminology_search_app.databinding.ListItemItTerminologyBinding

class ITTerminologyListAdapter(
    private val layoutInflater: LayoutInflater,
    private val itTerminologyList: List<ITTerminology>
    ): RecyclerView.Adapter<ITTerminologyListAdapter.ViewHolder>() {

    interface ITTerminologyListAdapterCallbackListener {
        fun onItemSelected(itTerminology: ITTerminology)
    }

    private var callbackListener: ITTerminologyListAdapterCallbackListener? = null

    override fun getItemCount() = itTerminologyList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ListItemItTerminologyBinding>(
            layoutInflater,
            R.layout.list_item_it_terminology,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itTerminologyList[position]
        val binding = DataBindingUtil.findBinding<ListItemItTerminologyBinding>(holder.itemView)

        holder.itemView.setOnClickListener {
            callbackListener?.onItemSelected(item)
        }

        holder.bind(item)
    }

    fun setOnClickListener(callbackListener: ITTerminologyListAdapterCallbackListener) {
        this.callbackListener = callbackListener
    }

    class ViewHolder(private val binding: ListItemItTerminologyBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(itTerminology: ITTerminology) {
            binding.terminologyTitleTextView.text = itTerminology.display_name
        }
    }
}