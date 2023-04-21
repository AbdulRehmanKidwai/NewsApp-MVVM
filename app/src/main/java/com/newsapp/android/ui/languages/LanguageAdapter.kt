package com.newsapp.android.ui.languages

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newsapp.android.data.model.languages.Language
import com.newsapp.android.databinding.SourceItemLayoutBinding
import com.newsapp.android.utils.AppConstant


class LanguageAdapter(private val languageList: ArrayList<Language>) :
    RecyclerView.Adapter<LanguageAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: SourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(language: Language){

                binding.tvItem.text=language.name

                binding.tvItem.setOnClickListener{

                    it.context.startActivity(Intent(it.context, LanguageNewsActivity::class.java)
                        .putExtra(AppConstant.LANGUAGE, language.id))

                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        return DataViewHolder(SourceItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
        parent, false))
    }

    override fun getItemCount(): Int =languageList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(languageList[position])
    }

    fun addData(list: List<Language>){
        languageList.clear()
        languageList.addAll(list)
    }
}




