package com.newsapp.android.ui.languages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newsapp.android.data.model.topHeadines.Article
import com.newsapp.android.databinding.TopHeadlineItemLayoutBinding
import com.newsapp.android.utils.Utils
import com.newsapp.android.utils.loadImage

class LanguageNewsAdapter(private val articleList: ArrayList<Article>) :
    RecyclerView.Adapter<LanguageNewsAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {

            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name

            article.imageUrl?.let { binding.imageViewBanner.loadImage(it) }

            itemView.setOnClickListener { Utils.openCustomTabUrl(it.context, article.url) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            TopHeadlineItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.bind(articleList[position])
    }

    fun addData(list: List<Article>) {
        articleList.addAll(list)
    }

}