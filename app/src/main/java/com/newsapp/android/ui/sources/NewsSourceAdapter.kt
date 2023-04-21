package com.newsapp.android.ui.sources

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newsapp.android.data.model.sources.Source
import com.newsapp.android.databinding.SourceItemLayoutBinding


class NewsSourceAdapter(private val sourceList: ArrayList<Source>) :
    RecyclerView.Adapter<NewsSourceAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: SourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(source: Source){

                binding.tvItem.text=source.name

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        return DataViewHolder(SourceItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
        parent, false))
    }

    override fun getItemCount(): Int =sourceList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(sourceList[position])
    }

    fun addData(list: List<Source>){
        sourceList.addAll(list)
    }
}




