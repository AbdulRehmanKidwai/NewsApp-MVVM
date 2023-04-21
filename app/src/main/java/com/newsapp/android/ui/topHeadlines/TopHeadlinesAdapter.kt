package com.newsapp.android.ui.topHeadlines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newsapp.android.data.local.entities.TopHeadlines
import com.newsapp.android.databinding.TopHeadlineItemLayoutBinding
import com.newsapp.android.utils.ItemClickListener
import com.newsapp.android.utils.loadImage

class TopHeadlinesAdapter(private val articleList: ArrayList<TopHeadlines>) :
    RecyclerView.Adapter<TopHeadlinesAdapter.DataViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<TopHeadlines>

    lateinit var javaItemClickListener: JavaItemClickListener

    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            article: TopHeadlines,
            itemClickListener: ItemClickListener<TopHeadlines>,
            javaItemClickListener: JavaItemClickListener
        ) {

            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.sourceName

            article.imageUrl?.let { binding.imageViewBanner.loadImage(it) }

            itemView.setOnClickListener {
                itemClickListener(article)
                javaItemClickListener.onClick(article)
            }
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

        holder.bind(articleList[position], itemClickListener, javaItemClickListener)
    }

    fun addData(list: List<TopHeadlines>) {
        articleList.addAll(list)
    }

}

interface JavaItemClickListener {

    fun onClick(top: TopHeadlines)
}