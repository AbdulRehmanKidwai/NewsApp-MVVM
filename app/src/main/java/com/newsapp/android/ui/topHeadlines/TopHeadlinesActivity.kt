package com.newsapp.android.ui.topHeadlines

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsapp.android.data.local.entities.TopHeadlines
import com.newsapp.android.databinding.ActivityTopHeadlinesBinding
import com.newsapp.android.di.component.ActivityComponent
import com.newsapp.android.ui.base.BaseActivity
import com.newsapp.android.ui.base.UiState
import com.newsapp.android.utils.Utils

import kotlinx.coroutines.launch
import javax.inject.Inject

class TopHeadlinesActivity :
    BaseActivity<ActivityTopHeadlinesBinding, TopHeadlinesViewModel>() {

    @Inject
    lateinit var adapter: TopHeadlinesAdapter


    private fun renderList(articleList: List<TopHeadlines>) {

        adapter.addData(articleList)
        adapter.notifyDataSetChanged()

    }

    override fun getViewBinding(): ActivityTopHeadlinesBinding {
        return ActivityTopHeadlinesBinding.inflate(layoutInflater)
    }

    override fun setUpUi() {

        binding.recyclerView.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.recyclerView.adapter = adapter

        adapter.itemClickListener = {
            it.url?.let { it1 -> Utils.openCustomTabUrl(this, it1) }
        }

        adapter.javaItemClickListener = object : JavaItemClickListener {
            override fun onClick(top: TopHeadlines) {
                top.url?.let { Utils.openCustomTabUrl(this@TopHeadlinesActivity, it) }
            }
        }
    }

    override fun setUpObserver() {


        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.articleList.collect {

                    when (it) {

                        is UiState.Success -> {

                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE

                            renderList(it.data)
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE

                            Toast.makeText(this@TopHeadlinesActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }


                    }

                }

            }
        }

    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

}