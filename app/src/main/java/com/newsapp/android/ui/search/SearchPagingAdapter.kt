package com.newsapp.android.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.newsapp.android.data.model.topHeadines.Article
import com.newsapp.android.databinding.TopHeadlineItemLayoutBinding
import com.newsapp.android.utils.Utils
import com.newsapp.android.utils.loadImage

class SearchPagingAdapter :
    PagingDataAdapter<Article, SearchPagingAdapter.DataViewHolder>(COMPARATOR) {

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

/*    override fun getItemCount(): Int = this.snapshot().items.size*/

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

}