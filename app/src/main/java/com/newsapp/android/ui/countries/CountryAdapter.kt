package com.newsapp.android.ui.countries

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newsapp.android.data.model.countries.Country
import com.newsapp.android.databinding.SourceItemLayoutBinding
import com.newsapp.android.ui.languages.LanguageNewsActivity
import com.newsapp.android.utils.AppConstant

class CountryAdapter(private val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: SourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(country: Country){

                binding.tvItem.text=country.name

                binding.tvItem.setOnClickListener{

                    it.context.startActivity(Intent(it.context, LanguageNewsActivity::class.java)
                        .putExtra(AppConstant.COUNTRY, country.id))
                }

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        return DataViewHolder(SourceItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
        parent, false))
    }

    override fun getItemCount(): Int =countryList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    fun addData(list: List<Country>){
        countryList.clear()
        countryList.addAll(list)
    }
}




